package hj.action.concurrent.JUC.tools.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * <p>Title : 测试</p>
 * <p>Description : </p>
 * <p>Date : 2019-01-10 </p>
 *
 * @author : hejie
 */
public class CountDownLatchTest {
    //要等五个人
    private static CountDownLatch countDownLatch = new CountDownLatch(5);
    //工人
    private static class Worker extends Thread{
        public void run(){
            //模拟干点别的事情
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //报数
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName()+"，已经报数了，开始干自己的事情...");
        }
    }

    public static void main(String[] args) {
        //第一个领导等待5个人的报数
        Thread leader1 = new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"，点名完毕！");
        });
        //第二个领导等待5个人的报数
        Thread leader2 = new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"，签到完毕！");
        });

        //勤奋的领导已经先到了
        leader1.start();
        leader2.start();

        for (int i = 0; i < 5; i++) {
            new Worker().start();
        }

        System.out.println(Thread.currentThread().getName()+"，我是外部线程！");
    }
}
