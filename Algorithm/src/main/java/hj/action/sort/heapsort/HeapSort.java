package hj.action.sort.heapsort;

import hj.action.sort.Sort;

/**
 * <p>Title : 堆排序，完全二叉树，最大堆实现</p>
 * <p>Description : </p>
 * <p>Date : 2018/12/18 </p>
 *
 * @author : hejie
 */
public class HeapSort implements Sort {
    private static int cnt = 0;
    //声明全局变量，用于记录数组array的长度；
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

        //1.构建一个完整最大堆，形成每个子树根节点均为最大值
        buildMaxHeap(array);
        /*
         * ①、将堆首与队尾交换，
         * ②、将堆长度 减1，
         * ③、重新整理 根堆，为最大堆
         * ④、重复1、2、3，直到堆长度为1
         */
        while (len > 0) {
            //当前堆顶为最大值，将其和队尾元素交换
            swap(array, 0, len - 1);
            len--;
            //重新调整 根堆，形成最大堆结构
            adjustHeap(array, 0);
        }

        return array;
    }

    /**
     * 构建完整的最大堆
     * @param array 原数组
     */
    private void buildMaxHeap(int[] array) {
        //从最后一个子树，开始向上构造最大堆
        for (int i = (len - 1) / 2; i >= 0; i--) {
            adjustHeap(array, i);
        }
    }

    /**
     * 每个子树构建最大堆
     * @param array 原数组
     * @param i 子树根节点
     */
    private void adjustHeap(int[] array, int i) {
        //大堆树节点的下标
        int max_index = i;

        //左子树节点的下标
        int left_sub_tree_index = i * 2 + 1;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (left_sub_tree_index < len && array[left_sub_tree_index] < array[max_index])
            max_index = left_sub_tree_index;

        //右子树节点的下标
        int right_sub_tree_index = i * 2 + 2;
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (right_sub_tree_index < len && array[right_sub_tree_index] < array[max_index])
            max_index = right_sub_tree_index;

        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
        if (max_index != i ) {
            swap(array, max_index, i);
            //递归，子树整理为最大堆
            adjustHeap(array, max_index);
        }
        cnt++;
    }

    /**
     * 交换元素
     * @param array 原数组
     * @param i 位置1
     * @param j 位置2
     */
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
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
        int[] b = heapSort.sort(a);
        for (int i : b) {
            System.out.print(i+",");
        }

        System.out.println("运算次数："+cnt);
    }


}
