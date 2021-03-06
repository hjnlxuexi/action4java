/**
 * <p>Title : 单例模式</p>
 * <p>Description :
 *
 * 1、只有一个实例
 * 2、只能被自己实例化
 * 3、自己向外提供访问点
 *
 * ====================================================================================================
 * 多线程环境下可能产生多个实例的问题：
 *      1、synchronized 同步整个创建实例的方法
 *      2、饿汉模式，直接在静态变量(类变量)上初始化实例
 *      3、double check，双重检查加锁，见：hj.action.concurrent.Volatile.DoubleCheckSingleton，注意：volatile的使用
 *      4、内部类实现，见：hj.action.concurrent.JMM.DCL.Singleton
 *
 * ====================================================================================================
 * 优点：
 *      1、节约了系统资源。由于系统中只存在一个实例对象，对与一些需要频繁创建和销毁对象的系统而言，单例模式无疑节约了系统资源和提高了系统的性能
 *      2、因为单例类封装了它的唯一实例，所以它可以严格控制客户怎样以及何时访问它
 * 缺点：
 *      1、由于单例模式中没有抽象层，因此单例类的扩展有很大的困难
 *      2、单例类的职责过重，在一定程度上违背了“单一职责原则”
 *
 * ====================================================================================================
 * 适用场景：
 *      1、系统只需要一个实例对象，如系统要求提供一个唯一的序列号生成器，或者需要考虑资源消耗太大而只允许创建一个对象。
 *      2、客户调用类的单个实例只允许使用一个公共访问点，除了该公共访问点，不能通过其他途径访问该实例。
 *
 * </p>
 * <p>Date : 2019-02-14 </p>
 *
 * @author : hejie
 */
package hj.action.DesignPatterns.Singleton;