/**
 * <p>Title : tomcat值得思考的问题</p>
 * <p>Description :
 *
 * 热加载：不应随便开启热加载属性 reloadable
 *      热加载，比人对应热卸载，不然就会内存泄漏
 *
 * 卸载类：
 *      卸载类的条件很苛刻，必须同时满足以下3点：
 *          1、该类所有的实例已经被回收
 *          2、加载该类的 ClassLoader 已经被回收
 *          3、该类对应的 java.lang.Class 对象没有任何地方被引用
 *
 *      tomcat 使用 ParallelWebappClassLoader 加载 Class，在热部署的时候自然也会回收该类加载器。
 *      但是要注意的是，ParallelWebappClassLoader 会作为线程上下文的类加载器，因此要避免该类加载器对象在其他地方被引用。
 *      其实，这个问题是最隐晦的，jdk 中有些类会持有线程上下文的类加载器，作为一个优秀的开源产品，tomcat 为我们解决了很多诸如此类的问题。
 *
 *
 * 热加载问题：
 *  1、文件锁：
 *      URLConnection 在读取jar资源时，会缓存，当再有其他部署任务读取该jar时则会异常，
 *      tomcat通过 JreMemoryLeakPreventionListener 禁用缓存 URLConnection.defaultUseCaches = false
 *  2、类加载器泄露：
 *      某些 JRE 库以单例的形式存在，它的生命周期很长甚至会贯穿于整个 java 程序，它们会使用上下文类加载器加载类，
 *      并且保留了类加载器的引用，所以会导致被引用的类加载器无法被回收
 *      tomcat 重加载 webapp 是创建一个新的类加载器来实现的，旧的类加载器无法被 gc 回收，致使其加载的 Class 也无法被回收，导致内存泄露。
 *
 *      解决思路也是一样的，只需要将当前上下文类加载器指定为系统类加载器即可：JreMemoryLeakPreventionListener
 *
 *  3、ThreadLocal 对象泄露：
 *      假如我们在 ThreadLocal 中保存了对象A，而且对象A由 ParallelWebappClassLoader 加载，那么就可以看成线程引用了对象A。
 *      由于 tomcat 中处理请求的是线程池，意味着该线程会存活很长一段时间。
 *      webapp 热加载时，会重新实例化一个 ParallelWebappClassLoader 对象，
 *      如果线程未销毁，那么旧的 ParallelWebappClassLoader 也无法被回收，导致内存泄露。
 *
 *      解决 ThreadLocal 内存泄露最好的办法，自然是把线程池中的所有的线程销毁并重新创建。
 *      这个过程分为两步：
 *          第一步：是将任务队列堵住，不让新的任务进来，
 *          第二步：是将线程池中所有线程停止。
 *      tomcat 解决该 ThreadLocal 对象泄露问题，也是借助了 Lifecycle 完成的，
 *      具体的实现类是 ThreadLocalLeakPreventionListener，它会处理 Lifecycle.AFTER_STOP_EVENT 事件，并且销毁线程池内的空闲线程。
 *
 * </p>
 * <p>Date : 2019-02-01 </p>
 *
 * @author : hejie
 */
package hj.action.tomcat.Problem;