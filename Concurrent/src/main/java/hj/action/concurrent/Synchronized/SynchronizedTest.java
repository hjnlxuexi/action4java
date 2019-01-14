package hj.action.concurrent.Synchronized;

/**
 * <p>Title : </p>
 * <p>Description :
 *
 * 1、普通同步方法，锁是当前实例对象
 * 2、静态同步方法，锁是当前类的class对象
 * 3、同步方法块，锁是括号里面的对象
 *
 * 通过 javap -c 查看类信息：
 *  同步代码块  是使用monitorenter和monitorexit指令实现的
 *  同步方法    则会被翻译成普通的方法调用和返回指令如:invokevirtual、areturn指令
 * http://cmsblogs.com/?p=2071
 * </p>
 *
 * <p>Date : 2018/12/18 </p>
 *
 * @author : hejie
 */
public class SynchronizedTest {

    //静态方法，以类对象作为锁
    public static synchronized  void tast0(){}

    //普通方法，以当前实例对象作为锁
    public synchronized void test1(){}

    public void test2(){
        //用当前实例对象作为锁
        synchronized (this){}
        //用类对象作为锁
        synchronized (SynchronizedTest.class){}
    }

}
