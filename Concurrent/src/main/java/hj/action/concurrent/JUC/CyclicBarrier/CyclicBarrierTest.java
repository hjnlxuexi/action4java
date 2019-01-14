package hj.action.concurrent.JUC.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-01-09 </p>
 *
 * @author : hejie
 */
public class CyclicBarrierTest {
    private static CyclicBarrier cyclicBarrier;
    static class CyclicBarrierRunner extends Thread{
        public void run(){
            System.out.println(Thread.currentThread().getName()+"，到！");
            //等待
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            //这句话会在集合完成后执行
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(5,()->{
            //最后到的线程来执行屏障工作
            System.out.println(Thread.currentThread().getName()+"集合，完毕！");
        });

        for (int i = 0; i < 5; i++) {
            new CyclicBarrierRunner().start();
        }

        //System.out.println(Thread.currentThread().getName()+"我是外部线程！");
    }
}
