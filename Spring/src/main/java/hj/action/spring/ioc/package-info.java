/**
 * <p>Title : IOC</p>
 * <p>Description :
 *
 * IOC = Inversion Of Control 控制反转；又称依赖注入：Dependency Injection，DI
 * 原本需要自己创建自己管理的对象，都通过IOC服务提供。
 *
 * 注入方式
 *  1、构造器注入
 *  2、setter注入
 *  3、接口注入
 *
 * 架构组件：
 *  1、Resource，资源。
 *      对不同资源的抽象：ClasspathResource、URLResource、FileSystemResource
 *      Spring对资源定义的加载器：ResourceLoader
 *  2、BeanFactory，内部维护装有 BeanDefinition 的 Map容器。
 *      可罗列的bean工厂ListableBeanFactory、分层的bean工厂HierarchicalBeanFactory、可自动装箱的bean工厂AutowireCapableBeanFactory
 *      实现 DefaultListableBeanFactory
 *  3、BeanDefinition：Spring用来描述Bean的对象
 *  4、BeanDefinitionReader：将Spring中的 Resource 转化为 BeanDefinition
 *  5、ApplicationContext：应用上下文
 *      继承于BeanFactory，功能更强大：
 *          ①、继承 MessageSource，提供国际化的标准访问策略。
 *          ②、继承 ApplicationEventPublisher ，提供强大的事件机制。
 *          ③、扩展 ResourceLoader，可以用来加载多个 Resource，可以灵活访问不同的资源。
 *          ④、对 Web 应用的支持。
 *
 * </p>
 * <p>Date : 2019-01-21 </p>
 *
 * @author : hejie
 */
package hj.action.spring.ioc;