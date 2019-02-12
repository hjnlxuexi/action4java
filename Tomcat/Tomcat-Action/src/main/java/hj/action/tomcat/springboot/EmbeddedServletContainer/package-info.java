/**
 * <p>Title : EmbeddedServletContainer：嵌入的Servlet容器</p>
 * <p>Description :
 *
 * Springboot支持三种内置的Servlet容器：
 *  Tomcat、Jetty、Undertow
 *      TomcatEmbeddedServletContainer
 *      JettyEmbeddedServletContainer
 *      UndertowEmbeddedServletContainer
 *
 * 从注解说起：
 *  @ SpringBootApplication 多个注解的组合
 *      @ SpringBootConfiguration
 *      ......
 *      @ EnableAutoConfiguration
 *
 * 注解@ EnableAutoConfiguration，在spring-boot-autoconfigure模块中定义
 *      模块中META-INFO/spring.factories文件中，有大量的SPI配置
 *      可以看到：org.springframework.boot.autoconfigure.EnableAutoConfiguration=后面有一大堆的引入实现
 *      其中包含：EmbeddedServletContainerAutoConfiguration：内嵌servlet容器的配置
 *          创建内嵌Servlet容器工厂AbstractEmbeddedServletContainerFactory：TomcatEmbeddedServletContainerFactory、JettyEmbeddedServletContainerFactory、UndertowEmbeddedServletContainerFactory
 *              AbstractEmbeddedServletContainerFactory.getEmbeddedServletContainer创建Servlet容器：TomcatEmbeddedServletContainer......
 *
 *
 * 流程：
 *  springboot-tomcat.png
 *  0、SpringApplication.initialize() 初始化 通过SPI加载其他模块的类，并注册到spring容器中
 *  1、SpringApplication启动refresh
 *  2、EmbeddedWebApplicationContext 在执行 onRefresh 方法的时候，首先调用父类的 onRefresh，
 *  3、从容器中获取 EmbeddedServletContainerFactory 的实现类。
 *      由于 第0步 我们在 classpath 下面可以获取 tomcat 的 jar 包，因此 EmbeddedServletContainerAutoConfiguration 会在 spring 容器中注册 TomcatEmbeddedServletContainerFactory 这个 bean。
 *  4、由 TomcatEmbeddedServletContainerFactory 创建 TomcatEmbeddedServletContainer
 *      4.1、创建Tomcat对象
 *      4.2、创建TomcatEmbeddedServletContainer容器对象
 *      4.3、执行容器初始化
 *      4.4、启动tomcat
 *
 * </p>
 * <p>Date : 2019-01-30 </p>
 *
 * @author : hejie
 */
package hj.action.tomcat.springboot.EmbeddedServletContainer;