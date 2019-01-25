package hj.action.spring.ioc.LoadBean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * <p>Title : 容器初始化阶段：加载BeanDefinition</p>
 * <p>Description : </p>
 * <p>Date : 2019-01-22 </p>
 *
 * @author : hejie
 */
public class LoadBeanDefinition {

    public static void main(String[] args) {
        //第一步：获取资源
        ClassPathResource resource = new ClassPathResource("bean.xml");
        //第二步：创建Bean工厂，IOC容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //第三步：根据创建BeanFactory 创建一个BeanDefinitionReader对象，用于资源解析
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        //第四步：装载资源；分为三个步骤：资源定位、解析装载、注册
        reader.loadBeanDefinitions(resource);

        /*
         * 资源定位：我们一般用外部资源(如xml)来描述 Bean 对象，所以在初始化 IOC 容器的第一步就是需要定位这个外部资源。
         * 装载：BeanDefinitionReader读取/解析Resource，转换为BeanDefinition；也就是将资源文件中定义的Bean的信息装载到IOC定义的数据结构BeanDefinition中。
         * 注册：向IOC容器将解析好的BeanDefinition，放到一个map钟进行管理，这个动作就叫做：注册
         *      通过BeanDefinitionRegistry接口实现注册，这里其实就是DefaultListableBeanFactory这个Bean工厂实现了该接口
         */

        /*
        reader.loadBeanDefinitions(resource);语句内部调用的核心功能是：
            doLoadBeanDefinitions，装载BeanDefinitions
                1、调用 getValidationModeForResource() 获取 xml 文件的验证模式。
                2、调用 loadDocument() 根据 xml 文件获取相应的 Document 实例。
                3、调用 registerBeanDefinitions() 注册 Bean 实例。
         */



        /*
        获取xml文件的验证模式：XML文件的验证模式保证了XML文件的正确性
            DTD(Document Type Definition)，即文档类型定义，为 XML 文件的验证机制，属于 XML 文件中组成的一部分(头部)。相当于XML中的“词汇”和“语法”，但DTD的约束性、解析、扩展性、表达都比较弱
            XSD（XML Schemas Definition）即XML Schema语言，本身就是XML。
        getValidationModeForResource()
         */



        /*
        获取Document对象：loadDocument()
            EntityResolver 的作用就是应用本身可以提供一个如何寻找验证文件(DTD/XSD)的方法，即自定义实现。
                publicId：被引用的外部实体的公共标识符
                systemId：被引用的外部实体的系统标识符
                如：
                XSD 验证模式
                    publicId：null
                    systemId：http://www.springframework.org/schema/beans/spring-beans.xsd
                DTD 验证模式
                    publicId：-//SPRING//DTD BEAN 2.0//EN
                    systemId：http://www.springframework.org/dtd/spring-beans.dtd
         */
    }
}
