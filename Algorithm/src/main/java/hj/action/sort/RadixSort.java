package hj.action.sort;

import java.util.ArrayList;

/**
 * <p>Title : 基数排序，非比较排序</p>
 * <p>Description :
 *  步骤：
 *      1、取得数组中的最大数，并取得位数；
 *      2、arr为原始数组，从最低位开始取每个位组成radix数组；
 *      3、对radix进行计数排序（利用计数排序适用于小范围数的特点）
 *
 *
 * 基数排序 vs 计数排序 vs 桶排序
    这三种排序算法都利用了桶的概念，但对桶的使用方法上有明显差异：
    1、计数排序：每个桶只存储单一键值
    2、桶排序：每个桶存储一定范围的数值
    3、基数排序：根据元素的每位数字来分配桶
 *
 * </p>
 * <p>Date : 2018/12/25 </p>
 *
 * @author : hejie
 */
public class RadixSort implements Sort{
    public static int cnt = 0;
    /**
     * 排序方法
     *
     * @param array 待排序数组
     * @return 排序结果
     */
    @Override
    public int[] sort(int[] array) {
        if (array == null || array.length < 2)
            return array;
        // 1.先算出最大数的位数；
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        //最大数的位数，决定跑多少趟
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
        //2、构建10个桶
        for (int i = 0; i < 10; i++)
            bucketList.add(new ArrayList<Integer>());


        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            //3、从个位数开始，每一趟，将对应的元素放入对应的桶中
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }

            //4、将所有桶的数据放入原始数组
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++)
                    array[index++] = bucketList.get(j).get(k);
                bucketList.get(j).clear();

                cnt++;
            }
        }

        return array;
    }


    public static void main(String[] args) {
        RadixSort radixSort = new RadixSort();
        int[] a = {87, 76, 65, 54, 43, 32, 21, 89, 98, 75, 36, 19, 40};
        int[] a1 = {19,21,32,36,40,43,54,65,75,76,87,89,98};//有序
        int[] b = radixSort.sort(a);
        for (int i : b) {
            System.out.print(i+",");
        }

        System.out.println("运算次数："+cnt);
    }
}
