package hj.action.GarbageCollection.AllocationAndCollection;

/**
 * <p>Title : 内存分配</p>
 * <p>Description :
 *
 * 1、优先分配在新生代的Eden区
 * 2、Eden区空间不够，发生Minor GC，将对象移动到一个Survivor中
 * 3、Eden和Survivor都放不下，则放到老年代
 *
 * </p>
 * <p>Date : 2019-01-07 </p>
 *
 * @author : hejie
 */
public class AllocationTest {

    private static final int _1MB = 1024 * 1024;

    /**
     * -verbose:gc -XX:+PrintGCDetails -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8
     *
     * JVM 堆大小固定为20M
     *
     * 新生代分配：10M (可用空间9M)
     * Eden：8M
     * Survivor*2：1M * 2
     *
     * 老年代：10M
     *
     */
    public static void testAllocation() {
        byte[] a1,a2,a3,a4;
        a1 = new byte[2 * _1MB]; // 2M
        a2 = new byte[2 * _1MB]; // 2M
        a3 = new byte[2 * _1MB]; // 2M

        a4 = new byte[4 * _1MB]; // 4M，发生Minor GC
    }

    public static void main(String[] args) {
        testAllocation();
    }

}
