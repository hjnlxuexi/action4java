package hj.action.sort;

import java.util.ArrayList;

/**
 * <p>Title : 桶排序，加强版的计数排序，非比较算法</p>
 * <p>Description :
 * 原理：
 *  加强版的计数排序
 *  假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排）
 *
 * 步骤：
 *      1、人为设置一个BucketSize，作为每个桶所能放置多少个不同数值；
 *      2、遍历输入数据，并且把数据一个一个放到对应的桶里去；
 *      3、对每个不是空的桶进行排序，可以使用其它排序方法，也可以递归使用桶排序；
 *      4、从不是空的桶里把排好序的数据拼接起来。
 *
 * 注意：
 *  如果递归使用桶排序为各个桶排序，则当桶数量为1时要手动减小BucketSize，否则会陷入死循环，导致内存溢出
 *
 *
 * 总结：
 *  桶划分的越小，各个桶之间的数据越少，排序所用的时间也会越少。但相应的空间消耗就会增大。
 *
 * </p>
 * <p>Date : 2018/12/25 </p>
 *
 * @author : hejie
 */
public class BucketSort implements Sort{
    public static int cnt = 0;

    /**
     * 桶排序
     * @param array 输入数组
     * @param bucketSize 桶大小
     * @return
     */
    public static ArrayList<Integer> bucketSort(ArrayList<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2)
            return array;

        int max = array.get(0), min = array.get(0);
        //1、 找到最大值最小值
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max)
                max = array.get(i);
            if (array.get(i) < min)
                min = array.get(i);
        }

        //2、桶数量，构建桶数组
        int bucketCount = (max - min) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        //3、将数据放到对应的桶中
        for (int i = 0; i < array.size(); i++) {
            bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
        }

        //4、递归桶排序，将结果放到结果列表
        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            //如果递归使用桶排序为各个桶排序，则当桶数量为1时要手动减小BucketSize，否则会陷入死循环，导致内存溢出
            if (bucketCount == 1) bucketSize--;
            ArrayList<Integer> temp = bucketSort(bucketArr.get(i), bucketSize);
            for (int j = 0; j < temp.size(); j++){
                resultArr.add(temp.get(j));
                cnt++;
            }
        }
        return resultArr;
    }


    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        Integer[] a = {87, 76, 65, 54, 43, 32, 21, 89, 98, 75, 36, 19, 40};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            Integer integer = a[i];
            list.add(integer);
        }


        ArrayList<Integer> b = bucketSort.bucketSort(list,2);
        for (int i : b) {
            System.out.print(i+",");
        }

        System.out.println("运算次数："+cnt);
    }

    /**
     * 排序方法
     *
     * @param array 待排序数组
     * @return 排序结果
     */
    @Override
    public int[] sort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        return array;
    }

}
