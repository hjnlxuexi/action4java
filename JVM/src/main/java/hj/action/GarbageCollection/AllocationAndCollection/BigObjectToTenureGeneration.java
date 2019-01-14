package hj.action.GarbageCollection.AllocationAndCollection;

/**
 * <p>Title : 大对象直接分配到老年代</p>
 * <p>Description :
 *
 * -XX:PretenureSizeThreshold  字节数
 * 注意：只对Serial和ParNew两款收集器有效！！！！
 *
 * </p>
 * <p>Date : 2019-01-07 </p>
 *
 * @author : hejie
 */
public class BigObjectToTenureGeneration {
    private static final int _1MB = 1024 * 1024;

    /**
     * -verbose:gc -XX:+PrintGCDetails -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     *      JVM 堆大小固定为20M
     *
     *      新生代分配：10M (可用空间9M)
     *      Eden：8M
     *      Survivor*2：1M * 2
     *
     *      老年代：10M
     *
     *      超过3145728 bytes = 3M 的内存直接在老年代分配
     */
    private static void testPretenureSizeThreshold(){
        byte[] a;
        a = new byte[4 * _1MB];  //直接分配到老年代
    }


    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }

}
