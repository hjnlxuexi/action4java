/**
 * <p>Title : tomcat如何使用类加载器</p>
 * <p>Description :
 *
 * java体系的类加载器：
 *      1、启动类加载器（Bootstrap ClassLoader）：
 *          加载对象是java的核心类库，把一些的 java 类加载到 jvm 中，
 *          它并不是我们熟悉的 ClassLoader，而是 jvm 层面由 C/C++ 实现的类加载器，
 *          负责加载 $JAVA_HOME/jre/lib 目录下 jvm 指定的类库，
 *          它是无法被 java 应用程序直接使用的
 *      2、扩展类加载器（Extension ClassLoader）：
 *          它是一个 ClassLoader 实例，父加载器是启动类加载器，
 *          它负责加载 $JAVA_HOME/jre/lib/ext 目录的类库
 *      3、应用类加载器（Application ClassLoader）：
 *          又叫做系统类加载器(System ClassLoader)，
 *          负责加载用户类路径（-cp参数）指定的类库，可以通过 ClassLoader.getSystemClassLoader() 获取，
 *          它也是由启动类加载器加载的
 *          通常我们在编程时，默认都是使用的这个类加载器
 *      4、自定义类加载器：
 *          应用程序根据自己的需求开发的类加载器，可以继承 ClassLoader，当然也可以不继承
 *
 *
 * 注：类加载器不局限于 ClassLoader，我们也可以自己实现一个类加载器，只要你加载出来的 Class 符合 jvm 规范即可
 *
 * 双亲微拍模式：如果一个类加载器收到类加载的请求，它首先不会自己去尝试加载这个类，而是把这个请求委派给父类加载器完成。
 *              每个类加载器都是如此，只有当父加载器在自己的搜索范围内找不到指定的类时（即ClassNotFoundException），子加载器才会尝试自己去加载。
 *
 * 方法：
 *   通过 jvm 参数 -verbose:class 方便地定位某个类是从哪个jar包加载的
 *
 *
 * ------------------------------------------------------------------------------------------------------------
 * 要点：
 *  1、在 Java 中我们用完全类名来标识一个类，
 *      而在 JVM 层面，使用完全类名 + ClassLoader 对象实例 ID 作为唯一标识，
 *      因此使用不同实例的类加载器，加载的两个同名的类，他们的类实例是不同的，并且不能强制转换
 *  2、在双亲委派机制中，类加载器查找类时，是一层层往父类加载器查找的，最后才查看自己，如果都找不到则会抛出异常，而不是一层层往下找的
 *  3、每个运行中的线程都有一个 ClassLoader，并且会从父线程中继承（默认是应用类加载器），
 *      在没有显式声明由哪个类加载器加载类时（比如 new 关键字），将默认由当前线程的类加载器加载该类
 *
 * ------------------------------------------------------------------------------------------------------------
 * tomcat 类加载器要解决的问题：
 *  1、为了避免类冲突，每个 webapp 项目中各自使用的类库要有隔离机制
 *  2、不同 webapp 项目支持共享某些类库
 *  3、类加载器应该支持热插拔功能，比如对 jsp 的支持、webapp 的 reload 操作
 *
 * tomcat 类加载器：
 *      Common 类加载器：继承于应用程序类加载器，负责加载 ${catalina.base}/lib、${catalina.home}/lib 目录下面所有的 .jar 文件和 .class 文件
 *          catalina 类加载器：继承于 Common，默认与 Common 类加载器是同一个实例
 *          share 类加载器：继承于 Common，默认与Common 类加载器是同一个实例
 *          通过修改 catalina.properties 文件的 server.loader 和 shared.loader 配置，从而指定其创建不同的类加载器
 *
 * WebappClassloader:
 *      每个 webapp 使用单独的类加载器完成我们开发的 webapp 应用程序的类加载，而每一个 webapp 对应一个 WebappClassLoader。
 *      tomcat7 默认使用 WebappClassLoader 类加载器
 *      tomcat8 默认使用 ParallelWebappClassLoader，支持并行加载类的特性 并且一旦注册了并行加载的能力，就不能回退了，
 *          需要同时满足以下两点才支持并行加载类:
 *          1、没有创建调用者的实例
 *          2、调用者的所有超类（除了类对象）都是并行注册的
 *
 *      WebappClassLoaderBase 实现了主要的逻辑，并且继承了 Lifecycle，在 tomcat 组件启动、关闭时会完成资源的加载、卸载操作，
 *      例如在 start 过程会读取我们熟悉的 /WEB-INF/classes、/WEB-INF/lib 资源，并且记录每个 jar 包的时间戳方便重载 jar 包；
 *      而在组件 stop 的时候，会清理已经加载的资源；destroy 时会显式地触发 URLClassLoader.close()
 *
 * WebappLoader:
 *      单独的类加载器是无法获取 webapp 的资源信息的，因此 tomcat 引入了 WebappLoader，便于访问 Context 组件的信息，同时为 Context 提供类加载的能力支持
 *      内部属性：
 *          1、WebappClassLoaderBase classLoader，默认使用ParallelWebappClassLoader
 *          2、Context
 *          3、ClassLoader parentClassLoader，父加载器，默认为 catalina 类加载器
 *          4、reloadable = false，是否支持热加载类
 *      WebappLoader 在 stop 的时候，会销毁 WebappClassLoader(ParallelWebappClassLoader)，并且进行回收，促使 jvm 卸载已加载的类
 *
 *
 * 热加载：HotSwap
 *  为 Context 组件指定 reloadable 属性，如果设为 true，tomcat便会启用 HotSwap，定期扫描类文件的变动，
 *  如果有变动，则 重启 webapp 从而达到 HotSwap 的目的。
 *
 *  属性 reloadable 是由 Context 指定的，但是会通过 WebappLoader#setContext(Context context) 方法调用，从而传递给 WebappLoader
 *  可通过server.xml 设置 reloadable 属性。
 *
 *  WebappLoader 提供了后台定时任务的方法backgroundProcess()，
 *  Context 容器会间隔性地进行调用，它用于监听 class、jar 等文件的变更，一旦有变动，便会对 Context 容器进行 reload 操作
 *
 *
 * </p>
 * <p>Date : 2019-01-31 </p>
 *
 * @author : hejie
 */
package hj.action.tomcat.ClassLoader;