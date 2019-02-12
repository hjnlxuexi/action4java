/**
 * <p>Title : SPI：服务提供接口</p>
 * <p>Description :
 *
 * SPI = Service Provider Interface 服务提供接口
 *
 * 原理：反射机制
 *
 * JDK SPI： 通过java.util.ServiceLoader实现，模块化开发，解除耦合
 *  服务提供者模块(jar) 在META-INF/services目录下 添加接口文件，文件内容为接口实现类的全路径
 *      如：文件名为【example.MyService】，文件内容为【example.MyServiceImpl1,example.MyServiceImpl2】
 *  服务消费者模块(jar) 则通过ServiceLoader.load动态获取接口实例
 *
 * Spring SPI： 通过org.springframework.core.io.support.SpringFactoriesLoader 实现
 *  服务提供者 在META-INF/spring.factories文件中，记录接口与实现的键值对，即可：
 *      example.MyService=example.MyServiceImpl1,example.MyServiceImpl2 逗号分隔
 *  服务消费者 通过调用SpringFactoriesLoader.loadFactories 获取所有的SPI服务列表
 *
 *
 *
 * </p>
 * <p>Date : 2019-01-30 </p>
 *
 * @author : hejie
 */
package hj.action.tomcat.springboot.SPI;