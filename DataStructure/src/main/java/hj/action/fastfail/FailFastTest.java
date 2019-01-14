package hj.action.fastfail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>Title : java 集合的快速失败机制</p>
 * <p>Description :
 * 产生原因：
 * 当在读操作的过程中同时在发生写操作，无论单线程还是多线程环境下都有可能发生ConcurrentModificationException异常；
 * 但是fail-fast机制，不能保证一定会出现该错误
 *
 * 方案：使用CopyOnWriteArrayList来替换ArrayList
 *
 * </p>
 * <p>Date : 2018/12/24 </p>
 *
 * @author : hejie
 */
public class FailFastTest {

    private static List<Integer> list = new ArrayList<>();

    /**
     * 线程one迭代list
     */
    private static class threadOne extends Thread{
        public void run() {
            Iterator<Integer> iterator = list.iterator();
            while(iterator.hasNext()){
                int i = iterator.next();
                System.out.println("ThreadOne 遍历:" + i);
                //单线程中违反规则也会触发
                if(i == 3){
                    list.remove(i);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 当i == 3时，修改list
     */
    private static class threadTwo extends Thread{
        public void run(){
            int i = 0 ;
            while(i < 6){
                System.out.println("ThreadTwo run：" + i);
                if(i == 3){
                    list.remove(i);
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
        for(int i = 0 ; i < 10;i++){
            list.add(i);
        }
        new threadOne().start();
        //new threadTwo().start();
    }
}
