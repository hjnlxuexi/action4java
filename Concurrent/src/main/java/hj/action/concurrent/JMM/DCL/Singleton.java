package hj.action.concurrent.JMM.DCL;

/**
 * <p>Title : JMM的双检查锁</p>
 * <p>Description :
 * 1、基于volatile的双检查锁单例模式，见：hj.action.concurrent.Volatile.DoubleCheckSingleton。
 *  所采用的解决办法是：不允许初始化阶段步骤对象实例化时发生重排序
 *
 * 2、另一种解决办法：
 *     允许初始化阶段步骤对象实例化时发生重排序，但是不允许其他线程“看到”这个重排序。
 *
 *   利用classloder的机制来保证初始化instance时只有一个线程。JVM在类初始化阶段会获取一个锁，这个锁可以同步多个线程对同一个类的初始化。
 *   Java语言规定，对于每一个类或者接口C,都有一个唯一的初始化锁LC与之相对应。从C到LC的映射，由JVM的具体实现去自由实现。
 *   JVM在类初始化阶段期间会获取这个初始化锁，并且每一个线程至少获取一次锁来确保这个类已经被初始化过了。
 *
 *
 * </p>
 * <p>Date : 2018/12/26 </p>
 *
 * @author : hejie
 */
public class Singleton {
    private Singleton(){
        System.out.println("init");
    }

    /**
     * 通过内部类
     * 优点：只有主动调用内部类是才会被初始化，规避饿汉模式的空间浪费
     */
    private static class SingletonHolder{
        SingletonHolder(){
            System.out.println("init->inner");
        }
        public static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.singleton;
    }


    /**
     * 通过私有的静态成员实现，叫做：饿汉模式
     * 缺点：无论有没有被调用，都会初始化
     */
    private static Singleton singleton = new Singleton();
    public static Singleton getInstance2(){
        return Singleton.singleton;
    }


    public static void main(String[] args) {
        System.out.println("main.....");
        Singleton.getInstance();//此时初始化内部类
        Singleton.getInstance();
    }

}
