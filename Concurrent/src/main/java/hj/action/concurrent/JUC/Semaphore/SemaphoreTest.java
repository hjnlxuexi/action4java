package hj.action.concurrent.JUC.Semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title : 实现停车场的问题</p>
 * <p>Description :
 *
 * 停车场仅有5个停车位，一开始停车场没有车辆所有车位全部空着，然后先后到来三辆车，停车场车位够，安排进去停车，
 * 然后又来三辆，这个时候由于只有两个停车位，所有只能停两辆，其余一辆必须在外面候着，直到停车场有空车位，当然以后每来一辆都需要在外面候着。
 * 当停车场有车开出去，里面有空位了，则安排一辆车进去（至于是哪辆 要看选择的机制是公平还是非公平）
 *
 * </p>
 * <p>Date : 2019-01-10 </p>
 *
 * @author : hejie
 */
public class SemaphoreTest {

    /**
     * 停车场
     */
    private static class Parking{
        /**
         * 信号量
         */
        private Semaphore semaphore;

        Parking(int permits){
            semaphore = new Semaphore(permits);
        }

        /**
         * 停车
         */
        public void park(){
            try {
                semaphore.acquire();

                Random random = new Random(47);
                int time = random.nextInt(5);
                System.out.println(Thread.currentThread().getName()+"，停车"+time+"秒");
                TimeUnit.SECONDS.sleep(time);
                System.out.println(Thread.currentThread().getName()+"，开出停车场");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //1、正常开出停车场
                //2、车在停车场报废了，强行清理
                semaphore.release();
            }
        }
    }

    /**
     * 车
     */
    private static class Car extends Thread{
        //车子要使用停车场的停车功能，所以采用组合关系
        Parking parking;
        Car(Parking parking){
            this.parking = parking;
        }

        public void run(){
            //车子开进停车场
            parking.park();
        }
    }

    public static void main(String[] args) {
        Parking parking = new Parking(5);
        //10辆车，5个车位
        for (int i = 0; i < 10; i++) {
            new Car(parking).start();
        }
    }


}
