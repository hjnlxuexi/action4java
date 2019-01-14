package hj.action.concurrent.Volatile;

import java.util.concurrent.CountDownLatch;

/**
 * <p>Title : Volatile </p>
 * <p>Description :
 *
 * 保证线程间变量的可见性。
 * 如果一个变量使用volatile，则它比使用synchronized的成本更加低，因为它不会引起线程上下文的切换和调度。
 *
 * 原子性：NO
 * 可见性：YES
 * 有序性：YES
 *
 * 使用条件（同时满足）：
 * 1、对变量的写操作不依赖于当前值。
 * 2、该变量没有包含在具有其他变量的不变式中。
 *
 * </p>
 * <p>Date : 2018/12/18 </p>
 *
 * @author : hejie
 */
public class VolatileTest {
    private volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest test = new VolatileTest();
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //非原子性
                test.i++;

                //可见性
                System.out.println(Thread.currentThread().getName()+"，i="+test.i);
                countDownLatch.countDown();
            },"thread-"+i).start();
        }
        countDownLatch.await();
        //非 原子性，与期望值不同
        System.out.println("i="+test.i);
    }
}
