/**
 * <p>Title : Tomcat 架构设计</p>
 * <p>Description :
 *
 * Server：
 *
 *  Service：用于连接Engine和Server
 *
 *      Connector： 是tomcat中TCP的连接实现，支持HTTP、AJP(Apache JServer Protocol)
 *
 *      Engine：是Catalina的顶层容器，表示整个Catalina的servlet引擎
 *
 *          Host：容器，表示一个拥有若干个Context的虚拟主机
 *
 *              Context：容器，表示一个Web应用，一个context包含一个或多个wrapper
 *                  Context代表一个独立的web应用，针对每个Context，tomcat都是使用不同的Classloader避免类冲突。
 *                  如果我们希望使用一个自定义的目录作为部署路径的话，可以在server.xml中Host下新增Context即可
 *
 *                  Wrapper：容器，表示一个独立的servlet
 *
 *      Valve：阀门
 *
 * Container：容器
 *  Engine、Host、Context、Wrapper都属于Container，
 *  此外，容器中还包含一系列的Loader、Logger、Manager、Realm和Resource等
 *
 * Realm：域
 *  是用于对单个用户进行身份验证的底层安全领域的只读外观，并标识与这些用户相关联的安全角色。
 *  域可以在任何容器级别上附加，但是通常只附加到Context，或者更高级别的容器。
 *
 *  --------------------------------------------------------------------------------------------------------------
 * 代码结构：
 *  org.apache.catalina包：Tomcat的核心模块，包括了HttpServlet、HttpSession的实现，以及各大组件的实现
 *  org.apache.coyote：这块主要用于支持各种协议，比如http1.1、http2.0、ajp等，代码量较少
 *  org.apache.tomcat：tomcat的基础包，包括了数据库连接池、websocket实现、tomcat的jni、工具类。
 *                      org.apache.tomcat.util包的代码量也不少,其中还包括了对jdk源码的扩展，比如线程池。
 *
 * </p>
 * <p>Date : 2019-01-25 </p>
 *
 * @author : hejie
 */
package hj.action.tomcat.framework;