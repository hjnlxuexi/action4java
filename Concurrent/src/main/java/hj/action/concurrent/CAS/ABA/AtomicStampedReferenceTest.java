package hj.action.concurrent.CAS.ABA;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * <p>Title : 通过AtomicStampedReference解决CAS的ABA问题</p>
 * <p>Description : </p>
 * <p>Date : 2019-01-09 </p>
 *
 * @author : hejie
 */
public class AtomicStampedReferenceTest {
    private static AtomicInteger ati = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) throws InterruptedException {
        Thread ai1 = new Thread(() -> {
            ati.compareAndSet(100, 110);
            ati.compareAndSet(110, 100);
            //第一个100 和 第二个100 已经不是同一个东西了
        });
        Thread ai2 = new Thread(() -> {
            //等第一个线程执行完
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AtomicInteger: " + ati.compareAndSet(100, 120));
        });
        ai1.start();
        ai2.start();
        ai1.join();
        ai2.join();


        Thread asr1 = new Thread(() -> {
            //等第二个线程拿到stamp
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            asr.compareAndSet(100, 110, asr.getStamp(), asr.getStamp() + 1);
            asr.compareAndSet(110, 100, asr.getStamp(), asr.getStamp() + 1);
        });

        Thread asr2 = new Thread(() -> {
            int stamp = asr.getStamp();

            //等第一个线程执行完
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("AtomicStampedReference: " + asr.compareAndSet(100, 120, stamp, stamp + 1));
        });

        asr1.start();
        asr2.start();
    }

}
