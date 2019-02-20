/**
 * <p>Title : 享元模式</p>
 * <p>Description :
 *
 *  所谓享元模式就是运行共享技术有效地支持大量细粒度对象的复用。
 *  共享模式是支持大量细粒度对象的复用，所以享元模式要求能够共享的对象必须是细粒度对象。
 *
 *  内部状态：在享元对象内部不随外界环境改变而改变的共享部分。-----成员变量
 *  外部状态：随着环境的改变而改变，不能够共享的状态就是外部状态。----方法参数
 *
 *  内部状态存储于享元对象内部，而外部状态则应该由客户端来考虑。
 *
 *  Flyweight: 抽象享元类。所有具体享元类的超类或者接口，通过这个接口，Flyweight可以接受并作用于外部状态
 *  ConcreteFlyweight: 具体享元类。指定内部状态，为内部状态增加存储空间。
 *  UnsharedConcreteFlyweight: 非共享具体享元类。指出那些不需要共享的Flyweight子类。
 *  FlyweightFactory: 享元工厂类。用来创建并管理Flyweight对象，它主要用来确保合理地共享Flyweight，
 *      当用户请求一个Flyweight时，FlyweightFactory就会提供一个已经创建的Flyweight对象或者新建一个（如果不存在）。
 *
 *  总结：把享元对象放到共享容器中，复用
 *
 * </p>
 * <p>Date : 2019-02-19 </p>
 *
 * @author : hejie
 */
package hj.action.DesignPatterns.Flyweight;