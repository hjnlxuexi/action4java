/**
 * <p>Title : Catalina初始化</p>
 * <p>Description :
 *
 * Bootstrap：是tomcat的入口，它会完成初始化ClassLoader，实例化Catalina以及load、start动作。
 *  main()：入口，
 *      1、创建Bootstrap实例，new Bootstrap();
 *      2、初始化init()
 *          2.1、initClassLoaders：初始化各种ClassLoader，commonLoader、catalinaLoader、sharedLoader
 *          2.2、通过反射实例化 Catalina对象
 *          2.3、通过反射调用 Catalina实例的setParentClassLoader，设置父ClassLoader为 sharedLoader
 *      3、setAwait():
 *          通过反射调用Catalina的setAwait()，目的是为了让tomcat在关闭端口阻塞监听关闭命令
 *      4、load()
 *          通过反射调用Catalina的load()，初始化一些资源，优先加载conf/server.xml，找不到再去加载server-embed.xml；
 *          实例化Server、Service、Connector、Engine、Host等组件，并调用Lifecycle#init()完成初始化动作，以及发出INITIALIZING、INITIALIZED事件
 *      5、start()
 *          通过反射调用Catalina的start()
 *
 * </p>
 * <p>Date : 2019-01-26 </p>
 *
 * @author : hejie
 */
package hj.action.tomcat.startup;