/**
 * <p>Title : springboot内嵌tomcat的配置</p>
 * <p>Description :
 *
 * 配置以server开头
 *  server:
 *      port: 服务端口8080
 *      context-path: 服务上下文根路径
 *      tomcat[jetty/undertow]:
 *          max-threads: 最大线程数
 *          max-connections: 最大允许的连接数，nio默认10000
 *          accept-count: 达到 max-connections 之后，允许等待的连接数量
 *
 * 配置的生效主要通过 ServerProperties 来完成
 *      实现 EmbeddedServletContainerCustomizer 接口，从而能够通过customize()方法进行参数配置
 *      然而 customize() 何时被调用？   通过 BeanPostProcessor 接口的实例：EmbeddedServletContainerCustomizerBeanPostProcessor
 *
 *
 * </p>
 * <p>Date : 2019-01-30 </p>
 *
 * @author : hejie
 */
package hj.action.tomcat.springboot.Config;