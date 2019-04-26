package hj.action.concurrent.Thread.WaitNotify;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title : 线程间通讯，wait、notify</p>
 * <p>Description : </p>
 * <p>Date : 2019-03-21 </p>
 *
 * @author : hejie
 */
public class Test {
    static boolean condition = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread wait = new Thread(new Waiter(),"wait");
        wait.start();
        TimeUnit.SECONDS.sleep(1);

        Thread notify = new Thread(new Notifier(), "notify");
        notify.start();
    }

    static class Waiter implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                if (condition){//while
                    try {
                        System.out.println("条件不满足，等待。。。。。。");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("正常业务代码。。。。。。");
            }
        }
    }

    static class Notifier implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                condition = false;
                lock.notify();

                try {
                    TimeUnit.SECONDS.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("已经发送通知，并释放锁");
            }
            //再获取锁
            synchronized (lock){
                System.out.println("再次获取锁！");
            }
        }
    }

}
