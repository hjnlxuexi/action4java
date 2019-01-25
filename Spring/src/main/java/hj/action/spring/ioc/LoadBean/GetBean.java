package hj.action.spring.ioc.LoadBean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * <p>Title : 通过IOC容器（BeanFactory）加载Bean</p>
 * <p>Description : </p>
 * <p>Date : 2019-01-24 </p>
 *
 * @author : hejie
 */
public class GetBean {

    public static void main(String[] args) {
        //第一步：获取资源
        ClassPathResource resource = new ClassPathResource("bean.xml");
        //第二步：创建Bean工厂，IOC容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //第三步：根据创建BeanFactory 创建一个BeanDefinitionReader对象，用于资源解析
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        //第四步：装载资源；分为三个步骤：资源定位、解析装载、注册
        reader.loadBeanDefinitions(resource);

        //第五步：获取Bean
        factory.getBean("myBean");
        /*
        1、分析从缓存中获取单例 bean，以及对 bean 的实例中获取对象
        2、如果从单例缓存中获取 bean，Spring 是怎么加载的呢？所以第二部分是分析 bean 加载，以及 bean 的依赖处理
        3、bean 已经加载了，依赖也处理完毕了，第三部分则分析各个作用域的 bean 初始化过程。
         */
    }
}
