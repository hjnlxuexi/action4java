package hj.action.concurrent.ThreadLocal;

/**
 * <p>Title : ThreadLocal实例</p>
 * <p>Description : </p>
 * <p>Date : 2019-01-17 </p>
 *
 * @author : hejie
 */
public class ThreadLocalTest {
    //ThreadLocal对象，初始化值
    private static ThreadLocal<Integer> seqCount = ThreadLocal.withInitial(() -> 0);

    //线程类
    private static class SeqThread extends Thread{

        private int nextSeq(){
            seqCount.set(seqCount.get() + 1);
            return seqCount.get();
        }

        public void run(){
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " seqCount: "+nextSeq());
            }
        }
    }

    public static void main(String[] args) {
        SeqThread thread1 = new SeqThread();
        SeqThread thread2 = new SeqThread();
        SeqThread thread3 = new SeqThread();
        SeqThread thread4 = new SeqThread();

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }


}
