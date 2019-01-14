package hj.action.concurrent.JMM.HappensBefore;

/**
 * <p>Title : happens-before原则：可见性</p>
 * <p>Description :
 *
 * 在JMM中，如果一个操作执行的结果需要对另一个操作可见，那么这两个操作之间必须存在happens-before关系
 * 原则：
 *  1. 如果一个操作happens-before另一个操作，那么第一个操作的执行结果将对第二个操作可见，而且第一个操作的执行顺序排在第二个操作之前。
 *  2. 两个操作之间存在happens-before关系，并不意味着一定要按照happens-before原则制定的顺序来执行。
 *      如果重排序之后的执行结果与按照happens-before关系来执行的结果一致，那么这种重排序并不非法。
 *
 * happens-before原则规则：
     程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作；
     锁定规则：一个unLock操作先行发生于后面对同一个锁额lock操作；
     volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作；
     传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C；
     线程启动规则：Thread对象的start()方法先行发生于此线程的每个一个动作；
     线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生；
     线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行；
     对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始；
 *
 * </p>
 * <p>Date : 2018/12/20 </p>
 *
 * @author : hejie
 */
public class HappensBefore {
    int i,j;
    volatile int vi;

    public static void main(String[] args) throws InterruptedException {
        HappensBefore happensBefore = new HappensBefore();

        //not-happens-before
        happensBefore.notHappensBefore();

        //be-happens-before
        happensBefore.beHappensBefore();
    }

    /**
     * 线程间无happens-before关系
     */
    private void notHappensBefore() throws InterruptedException {
        //第一个线程：i = 1
        Thread t1 = new Thread(()-> i=1);
        //第二个线程：j = i，  是否 j==1 ?
        Thread t2 = new Thread(()-> System.out.println(j=i));
        t1.start();
        t2.start();
    }

    /**
     * 线程间是happens-before关系
     *
     * vj=vj;  2个指令：1、读取vi，2、赋值给vj
     *
     * volatile保障happens-before关系：在1、2两个指令之间，vi值改变时，vj也会被赋予响应的新值；
     * 也可采用lock等方式来保障happens-before
     */
    private void beHappensBefore() throws InterruptedException {
        //第一个线程：vi = 1
        Thread t1 = new Thread(()-> vi=1);
        //第二个线程：j=vi，是否 j==1 ?
        Thread t2 = new Thread(()-> System.out.println(j=vi));
        t1.start();
        t2.start();
    }

}
