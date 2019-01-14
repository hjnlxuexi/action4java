package hj.action.GarbageCollection.AllocationAndCollection;

/**
 * <p>Title : 长期存活对象直接分配到老年代</p>
 * <p>Description :
 *
 * -XX:MaxTenuringThreshold  只Minor GC次数
 *
 *
 * </p>
 * <p>Date : 2019-01-07 </p>
 *
 * @author : hejie
 */
public class OldObjectToTenureGeneration {
    private static final int _1MB = 1024 * 1024;

    /**
     * -verbose:gc -XX:+PrintGCDetails -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     * -XX:+PrintTenuringDistribution
     *      JVM 堆大小固定为20M
     *
     *      新生代分配：10M (可用空间9M)
     *      Eden：8M
     *      Survivor*2：1M * 2
     *
     *      老年代：10M
     *
     *      存活15次的对象移动在老年代
     */
    private static void testTenuringThreshold() {
        byte[] a1,a2,a3,a4;
        a1 = new byte[_1MB/4];
        //a2 = new byte[_1MB/4];
        //什么时候计入老年代，取决于-XX:MaxTenuringThreshold设置
        a3 = new byte[4 * _1MB];
        a4 = new byte[4 * _1MB];
        a4 = null;
        a4 = new byte[4 * _1MB];
    }

    /**
     * 动态对象年龄判定：
     * 1、Survivor空间中相同年龄对象总和 > Survivor空间 / 2  ;
     * 2、Survivor中所有大于等于这些对象年龄的对象都移动到老年代
     *
     */
    private static void dynamicTenuringThreshold(){
        byte[] a1,a2,a3,a4;
        a1 = new byte[_1MB/4];
        //a1+a2大于Survivor空间的一半
        a2 = new byte[_1MB/4];

        a3 = new byte[4 * _1MB];
        a4 = new byte[4 * _1MB];
        a4 = null;
        a4 = new byte[4 * _1MB];

    }


    public static void main(String[] args) {
        //testTenuringThreshold();

        dynamicTenuringThreshold();
    }

}
