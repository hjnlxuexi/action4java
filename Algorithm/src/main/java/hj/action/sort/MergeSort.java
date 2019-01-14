package hj.action.sort;

import java.util.Arrays;

/**
 * <p>Title : 归并排序，分配额外空间</p>
 * <p>Description :
 * 1、把长度为n的输入序列分成两个长度为n/2的子序列；
 * 2、对这两个子序列分别采用归并排序；
 * 3、将两个排序好的子序列合并成一个最终的排序序列。
 * </p>
 * <p>Date : 2018/12/17 </p>
 *
 * @author : hejie
 */
public class MergeSort implements Sort {
    private static int cnt = 0;

    /**
     * 排序方法
     *
     * @param array 待排序数组
     * @return 排序结果
     */
    public int[] sort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        //拆分为两个数组
        int mid = array.length/2;
        int[] left = Arrays.copyOfRange(array,0,mid);
        int[] right = Arrays.copyOfRange(array,mid,array.length);

        //递归执行归并排序
        return merge(sort(left), sort(right));
    }

    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        //分配一个归并空间
        int[] result = new int[left.length + right.length];

        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];

            cnt++;
        }
        return result;
    }



    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] a = {87, 76, 65, 54, 43, 32, 21, 89, 98, 75, 36, 19, 40};
        int[] a1 = {19,21,32,36,40,43,54,65,75,76,87,89,98};//有序
        int[] b = mergeSort.sort(a);
        for (int i : b) {
            System.out.print(i+",");
        }

        System.out.println("运算次数："+cnt);
    }
}
