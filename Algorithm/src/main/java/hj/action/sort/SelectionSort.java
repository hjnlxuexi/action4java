package hj.action.sort;

/**
 * <p>Title : 选择排序</p>
 * <p>Description :
 * 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好
 * </p>
 * <p>Date : 2018/12/10 </p>
 *
 * @author : hejie
 */
public class SelectionSort implements Sort {
    private static int cnt = 0;
    /**
     * 排序方法
     *
     * @param array 待排序数组
     * @return 排序结果
     */
    public int[] sort(int[] array) {
        if (array == null || array.length == 0 || array.length == 1) {
            return array;
        }
        //第一层：已排序的数据位置
        for (int i = 0; i < array.length - 1; i++) {
            //第二层：在剩余的数据中挑选最小的
            int min = i;
            boolean flag = true;//优化，当后续的元素已经有序了，则不需要再遍历排序了
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                    flag = false;
                }

                cnt++;
            }

            if (flag)break;
            //与待排序的排头数据交换
            int tmp = array[i];
            array[i] = array[min];
            array[min] = tmp;
        }
        return array;
    }


    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();

        int[] a = {87, 76, 65, 54, 43, 32, 21, 89, 98, 75, 36, 19, 40};//无序
        int[] a1 = {19,21,32,36,40,43,54,65,75,76,87,89,98};//有序
        int[] b = selectionSort.sort(a);
        for (int i : b) {
            System.out.println(i);
        }
        System.out.println("运算次数："+cnt);
    }
}
