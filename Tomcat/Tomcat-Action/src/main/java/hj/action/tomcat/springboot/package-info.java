/**
 * <p>Title : springboot集成tomcat</p>
 * <p>Description :
 *
 * 1、springboot利用 spring 的 SPI 的机制加载 EmbeddedServletContainerAutoConfiguration 该配置类，
 *  将 TomcatEmbeddedServletContainerFactory 加载到 spring 容器中
 * 2、对 tomcat 容器进行配置的动作，由 BeanFactoryPostProcessor 完成，spring boot 内置了多种 EmbeddedServletContainerCustomizer，由 ServerConfig 完成对 servlet 容器的配置
 * 3、利用工厂模式创建 TomcatEmbeddedServletContainer，并且调用 org.apache.catalina.startup.Tomcat#start() 启动 tomcat 容器
 *
 * </p>
 * <p>Date : 2019-01-30 </p>
 *
 * @author : hejie
 */
package hj.action.tomcat.springboot;