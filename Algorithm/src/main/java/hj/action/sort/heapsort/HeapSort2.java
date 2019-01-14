package hj.action.sort.heapsort;

import hj.action.sort.Sort;

/**
 * <p>Title : 堆排序，最小堆实现</p>
 * <p>Description : </p>
 * <p>Date : 2018/12/18 </p>
 *
 * @author : hejie
 */
public class HeapSort2 implements Sort{
    private static int cnt = 0;
    private static int len;
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

        len = array.length;

        //1、构构建完整最小堆，每个子树均为最小堆
        makeMinHeap(array);

        /*
         * ①、将堆首与队尾交换，
         * ②、将堆长度 减1，
         * ③、重新整理整个堆，为最小堆
         * ④、重复1、2、3，直到堆长度为1
         */
        for (int i = len-1; i > 0 ; i--) {
            //交换队首元素
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            //整理根堆为最小堆
            minHeapFixdown(array, 0 , i);
        }

        return array;
    }

    /**
     * 整理完整的最小堆
     * @param array 原数组
     */
    private void makeMinHeap(int[] array) {
        for (int i = (len-1)/2; i >= 0 ; i--) {
            minHeapFixdown(array, i , len);
        }
    }

    /**
     * 将目标子树整理为最小堆
     * @param array 原数组
     * @param i 目标子树节点
     * @param len 待整理的长度
     */
    private void minHeapFixdown(int[] array, int i, int len) {
        int j = i * 2 + 1;
        while (j < len){
            cnt++;
            //选出左右子树最小的
            if (j+1<len && array[j+1] > array[j]){
                j++;
            }
            if (array[i]>=array[j]) break;

            //交换根元素
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
            //整理子树
            i=j;
            j = i * 2 + 1;
        }
    }

    public static void main(String[] args) {
        HeapSort2 heapSort2 = new HeapSort2();
        int[] a = {87, 76, 65, 54, 43, 32, 21, 89, 98, 75, 36, 19, 40};
        /*
         *                                    87[0]
         *                       /                              \
         *                 76[1]                                  65[2]
         *              /         \                             /       \
         *       54[3]              43[4]                  32[5]         21[6]
         *      /     \           /       \              /      \
         * 89[7]      98[8]   75[9]       36[10]   19[11]       40[12]
         */
        int[] a1 = {19,21,32,36,40,43,54,65,75,76,87,89,98};//有序
        int[] b = heapSort2.sort(a);
        for (int i : b) {
            System.out.print(i+",");
        }

        System.out.println("运算次数："+cnt);
    }
}
