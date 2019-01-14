package hj.action.concurrent.JUC.locks.Condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Title : Condition实现生产者消费者问题</p>
 * <p>Description : </p>
 * <p>Date : 2019-01-06 </p>
 *
 * @author : hejie
 */
public class ConditionTest {

    private LinkedList<String> buffer;    //容器
    private int maxSize ;           //容器最大
    private Lock lock;
    private Condition consumeCondition;//可以消费的条件
    private Condition produceCondition;//可以生产的条件

    ConditionTest(int maxSize){
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        consumeCondition = lock.newCondition();
        produceCondition = lock.newCondition();
    }

    public void produce(String string) throws InterruptedException {
        lock.lock();    //获取锁
        try {
            while (maxSize == buffer.size()){
                System.out.println("容器满了，停止生产");
                produceCondition.await();       //容器满了，停止生产
                System.out.println("容器有空间了，通知可以生产了");
            }

            buffer.add(string);
            consumeCondition.signal();         //有货了，通知可以消费了
        } finally {
            lock.unlock();      //记得释放锁
        }
    }

    public String consume() throws InterruptedException {
        String string;
        lock.lock();
        try {
            while (buffer.size() == 0){
                System.out.println("容器空了，停止消费");
                consumeCondition.await();    //容器空了，停止消费
                System.out.println("有货了，通知可以消费了");
            }
            string = buffer.poll();
            produceCondition.signal();      //容器有空间了，通知可以生产了
        } finally {
            lock.unlock();
        }
        return string;
    }


    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest(10);
        //1、生产线程
        Thread p1 = new Thread(()->{
            try {
                while (true) {
                    conditionTest.produce("toy");
                    Thread.sleep((int)(Math.random()*100));//随机，模拟生产能力的波动
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //2、消费线程
        Thread c1 = new Thread(()->{
            try {
                while (true) {
                    conditionTest.consume();
                    Thread.sleep((int)(Math.random()*100));//随机，模拟消费能力的波动
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        p1.start();
        c1.start();

    }
}
