/**
 * <p>Title : 代理模式</p>
 * <p>Description :
 *
 *  代理模式就是给一个对象提供一个代理，并由代理对象控制对原对象的引用。
 *
 *  Subject: 抽象角色。声明真实对象和代理对象的共同接口。
 *  Proxy: 代理角色。代理对象与真实对象实现相同的接口，所以它能够在任何时刻都能够代理真实对象。
 *      代理角色内部包含有对真实对象的引用，所以她可以操作真实对象，同时也可以附加其他的操作，相当于对真实对象进行封装。
 *  RealSubject: 真实角色。它代表着真实对象，是我们最终要引用的对象
 *
 *
 *  优点：
 *      1、代理模式能够协调调用者和被调用者，在一定程度上降低了系统的耦合度。
 *      2、代理对象可以在客户端和目标对象之间起到中介的作用，这样起到了保护目标对象的作用
 *  缺点：
 *      1、由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度变慢。
 *      2、实现代理模式需要额外的工作，有些代理模式的实现非常复杂。
 *
 *
 *  适用场景：
 *      1、远程代理：为一个对象在不同的地址空间提供局部代表。这样可以隐藏一个对象存在于不同地址空间的事实。
 *      2、虚拟代理：通过使用过一个小的对象代理一个大对象。这样就可以减少系统的开销。
 *      3、保护代理：用来控制对真实对象的访问权限。
 *
 *
 *  ------------------------------------------------------------------------------------------------------
 *  动态代理：
 *      java Proxy：
 *          java.lang.reflect.Proxy:生成动态代理类和对象；
 *          java.lang.reflect.InvocationHandler（处理器接口）：可以通过invoke方法实现对真实角色的代理访问。
 *          每次通过 Proxy 生成的代理类对象都要指定对应的处理器对象。
 *
 *          内部采用反射机制，动态生成基于接口的子类，并转换为二进制码。
 *
 *      CGLIB：
 *          动态生成一个要被代理类的子类，子类重写要代理的类的所有不是final的方法
 *          在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。
 *          使用字节码处理框架ASM，来转换字节码并生成新的类。
 *          它比使用java反射的JDK动态代理要快。
 *          它为没有实现接口的类提供代理，为JDK的动态代理提供了很好的补充。
 *
 *
 *
 * </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
package hj.action.DesignPatterns.Proxy;