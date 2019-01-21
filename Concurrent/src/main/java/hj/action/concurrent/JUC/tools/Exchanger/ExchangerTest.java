package hj.action.concurrent.JUC.tools.Exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * <p>Title : 模拟生产者-消费者问题</p>
 * <p>Description : </p>
 * <p>Date : 2019-01-14 </p>
 *
 * @author : hejie
 */
public class ExchangerTest {
    //生产批次
    private static final Integer BATCH = 5;
    //交换容器 的 容量
    private static final Integer SIZE = 3;

    private static class Producer extends Thread {
        //生产者的容器，和消费者要相互交换
        private List<String> buffer;

        //用于和消费者交换容器的对象
        private Exchanger<List<String>> exchanger;

        //生产总批次
        private int batch;

        Producer(List<String> buffer, Exchanger<List<String>> exchanger, int batch, String name){
            this.buffer = buffer;
            this.exchanger = exchanger;
            this.batch = batch;
            super.setName(name);
        }

        public void run(){
            for (int n = 0; n < batch; n++) {
                //开始生产，装入容器
                for (int i = 0; i < SIZE; i++) {
                    buffer.add("产品编号："+n+"--"+i);
                    System.out.println(super.getName()+"生产>>产品编号："+n+"--"+i);
                }

                //装满了，交换容器
                try {
                    System.out.println("等待消费者交换容器。");
                    buffer = exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static class Customer extends Thread {

        //生产者的容器，和消费者要相互交换
        private List<String> buffer;

        //用于和消费者交换容器的对象
        private Exchanger<List<String>> exchanger;

        //生产总批次
        private int batch;

        Customer(List<String> buffer, Exchanger<List<String>> exchanger, int batch){
            this.buffer = buffer;
            this.exchanger = exchanger;
            this.batch = batch;
        }

        public void run() {
            for (int n = 0; n < batch; n++) {
                //当前手里拿的空容器，尝试和生产者交换一个满的容器
                try {
                    System.out.println("等待生产者交换容器。");
                    buffer = exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //开始消费
                for (int i = 0; i < SIZE; i++) {
                    System.out.println("消费>>"+buffer.get(0));
                    buffer.remove(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        //两个容器
        List<String> produceBuffer = new ArrayList<>();
        List<String> consumerBuffer = new ArrayList<>();

        //交换对象
        Exchanger<List<String>> exchanger = new Exchanger<>();

        new Producer(produceBuffer, exchanger, BATCH,"一号").start();
        //new Producer(produceBuffer, exchanger, BATCH, "二号").start(); //不能多生产者，不然生产者之间也会相互交换
        new Customer(consumerBuffer, exchanger, 10).start();

        System.out.println("我是主线程");
    }

}
