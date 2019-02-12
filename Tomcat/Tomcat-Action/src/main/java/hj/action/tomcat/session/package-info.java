/**
 * <p>Title : Session：会话</p>
 * <p>Description :
 *
 * 组件结构：
 *  Host：
 *      Context：
 *          Manager：
 *              Session1
 *              Session2
 *              ......
 *          SessionListener1
 *          SessionListener2
 *          ....
 *
 *
 * Context 对应一个 webapp 应用，每个webapp有多个 HTTPSessionListener
 *  并且每个Session是独立管理的，而Session的创建、销毁由 Manager 组件完成：
 *      1、Manager内部维护了N个Session实例对象，
 *      2、Manager与StandardContext是一一对应关系
 *      3、Manager 创建、销毁Session时，需要借助StandardContext获取 HTTPSessionListener列表，并进行事件通知
 *      4、StandardContext 的后台线程会对 Manager 中过期 Session 进行清理
 *
 * --------------------------------------------------------------------------------------------------------
 * Manager接口实现：
 *      StandardManager
 *          Manager 默认实现，在内存中管理 session，宕机将导致 session 丢失；
 *          但是当调用 Lifecycle 的 start/stop 接口时，将采用 jdk 序列化保存 Session 信息，
 *          因此当 tomcat 发现某个应用的文件有变更进行 reload 操作时，这种情况下不会丢失 Session 信息
 *      DeltaManager
 *          增量 Session 管理器，用于Tomcat集群的会话管理器，
 *          某个节点变更 Session 信息都会同步到集群中的所有节点，
 *          这样可以保证 Session 信息的实时性，但是这样会带来较大的网络开销
 *      BackupManager
 *          用于 Tomcat 集群的会话管理器，
 *          与DeltaManager不同的是，某个节点变更 Session 信息的改变只会同步给集群中的另一个 backup 节点
 *      PersistentManager
 *          当会话长时间空闲时，将会把 Session 信息写入磁盘，从而限制内存中的活动会话数量；
 *          此外，它还支持容错，会定期将内存中的 Session 信息备份到磁盘
 *
 * --------------------------------------------------------------------------------------------------------
 * Session接口实现：
 *      StandardSession
 *          同时实现了 javax.servlet.http.HttpSession、org.apache.catalina.Session 接口
 *          并且对外提供的是 StandardSessionFacade 外观类，保证了 StandardSession 的安全，避免开发人员调用其内部方法进行不当操作。
 *
 *      org.apache.catalina.connector.Request 实现 HttpServletRequest 接口
 *          持有 StandardSession 的引用，对外也是暴露 RequestFacade 外观类
 *
 *      StandardManager 内部维护了其创建的 StandardSession，是一对多的关系，并且持有 StandardContext 的引用
 *      StandardContext 内部注册了 webapp 所有的 HttpSessionListener 实例
 *
 *
 * --------------------------------------------------------------------------------------------------------
 * 创建Session：create-session.png
 *  HttpServletRequest#getSession()，程序中真正拿到的 HttpServletRequest 其实是 RequestFacade，而 RequestFacade 是 Request 的门面模式
 *
 *  1、判断 Request 对象中是否存在 Session，
 *      如果存在，并且未失效则直接返回，因为在 tomcat 中 Request 对象是被重复利用的，只会替换部分组件，所以会进行这步判断。
 *      如果不存在 Session，则尝试根据 requestedSessionId 查找 Session，而该 requestedSessionId 会在 HTTP-Connector CoyoteAdapter类中进行赋值（通过检查 url中jsessionid参数，Cookie中的sessionId），
 *          如果存在 Session 的话则直接返回，
 *          如果不存在的话，则创建新的 Session，并且把 sessionId 添加到 Cookie 中，
 *          后续的请求便会携带该 Cookie，这样便可以根据 Cookie 中的sessionId 找到原来创建的 Session 了
 *  2、通过Manager创建session
 *      ManagerBase.createSession()
 *
 *
 * --------------------------------------------------------------------------------------------------------
 * Session清理：Container 容器的 Background 线程
 *
 *  backgroundProcessorDelay 参数默认值为 -1，单位为秒，即默认不启用后台线程，
 *  tomcat 的 Container 容器需要开启线程处理一些后台任务，比如监听 jsp 变更、tomcat 配置变动、Session 过期等等
 *  因此 StandardEngine 在构造方法中便将 backgroundProcessorDelay 参数设为 10（当然可以在 server.xml 中指定该参数），即每隔 10s 执行一次
 *
 *  tomcat 所有容器组件，都是继承至 ContainerBase 的，包括 StandardEngine、StandardHost、StandardContext、StandardWrapper，
 *  而 ContainerBase 在启动的时候，如果 backgroundProcessorDelay 参数大于 0 则会开启 ContainerBackgroundProcessor 后台线程，
 *  调用自己以及子容器的 backgroundProcess 进行一些后台逻辑的处理，
 *  和 Lifecycle 一样，这个动作是具有传递性的，也就是说子容器还会把这个动作传递给自己的子容器，
 *  如图background-processor.png，其中父容器会遍历所有的子容器并调用其 backgroundProcess 方法，
 *  而 StandardContext 重写了该方法，它会调用 StandardManager#backgroundProcess() 进而完成 Session 的清理工作。
 *  这种传递性的设计，真的，，，很牛逼
 *
 *
 * </p>
 * <p>Date : 2019-01-30 </p>
 *
 * @author : hejie
 */
package hj.action.tomcat.session;