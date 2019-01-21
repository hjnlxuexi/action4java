package hj.action.concurrent.JUC.containers.BlockingQueue.ArrayBlockingQueue;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Title : ArrayBlockingQueue 中的一个构造函数为何需要加锁</p>
 * <p>Description :
 * <p>
 * 探索一下为啥第三个构造函数会加锁，Doug大神说：Lock only for visibility, not mutual exclusion -- 为了可见性，而不是为了互斥
 * 那怎么就为了可见性呢？
 * 网上的原因：
 * 1、指令重排，导致对象已经分配地址，可以访问了，但是对象内部的数据还没有初始化完成。加锁 让各个线程之间可见
 * 2、每个线程有CPU专属的告诉缓存，没有刷新到主内存。加锁 让各个线程之间 可见--同步
 *
 *
 * </p>
 * <p>Date : 2019-01-20 </p>
 *
 * @author : hejie
 */
public class Test {
    //
    ArrayList<Integer> list = new ArrayList<>(20);
    final ReentrantLock lock = new ReentrantLock();

    /**
     * 验证第一个原因
     *
     * 通过lock保证数据可见性？？？？？
     */
    private static void reason_1(Test test) {
        final ReentrantLock lock = test.lock;
        ArrayList<Integer> list = test.list;

        Thread a = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    list.add(i);
                    System.out.println("a--"+i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        });

        Thread b = new Thread(() -> {
            for (;;){
                System.out.println("b--"+list.size());
            }
        });

        a.start();
        b.start();
    }


    public static void main(String[] args) {
        Test test = new Test();
        reason_1(test);
    }

}
