package hj.action.GarbageCollection.DeadOrNot;

/**
 * <p>Title : 引用计数法的GC</p>
 * <p>Description :
 *
 * 输出GC信息：
 * -verbose:gc   再加上下面的打印模式
 *
 * -XX:+printGC                 打印GC简要信息
 * -XX:+PrintGCDetails          打印GC详细信息
 * -XX:+PrintGCTimeStamps       打印CG发生的时间戳,这个时间是相对于JVM启动时间的一个相对时间.
 * -XX:+PrintGCDateStamps       打印CG发生的时间戳,是一个阅读性好的,普通的日期时间.
 *
 * </p>
 * <p>Date : 2018/12/31 </p>
 *
 * @author : hejie
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    //消耗内存，一遍在GC时看到效果
    private byte[] bigsize = new byte[2 * _1MB];

    public static void main(String[] args) throws InterruptedException {
        ReferenceCountingGC r1 = new ReferenceCountingGC();
        ReferenceCountingGC r2 = new ReferenceCountingGC();
        //循环引用的两个对象会被回收吗？
        r1.instance = r2;
        r2.instance = r1;

        r1 = null;
        r2 = null;

        //发生GC
        System.gc();

        Thread.sleep(2000);
        System.out.println("两个对象会回收吗？");

        /*
        结论：
            虽然两个对象相互引用，但是程序任然进行了GC，回收了两个对象，
            故：java虚拟机并没有采用"引用计数算法"
         */
    }
}
