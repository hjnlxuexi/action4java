package hj.action.sort;

/**
 * <p>Title : 冒泡排序</p>
 * <p>Description :
 * 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 * </p>
 * <p>Date : 2018/12/9 </p>
 *
 * @author : hejie
 */
public class BubbleSort implements Sort {
    private static int cnt = 0;
    /**
     * 排序方法
     * 1、比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3、针对所有的元素重复以上的步骤，除了最后一个；
     * 4、重复步骤1~3，直到排序完成。
     *
     * @param array 待排序数组
     * @return 排序结果
     */
    public int[] sort(int[] array) {
        if (array == null || array.length == 0 || array.length == 1) {
            return array;
        }
        //第一层：循环计数，i 为已经找到正确位置的元素。
        for (int i = 0; i < array.length; i++) {
            //第二层：执行比较，计数 j 为需要进行比较的元素，已经排序的就不需要比较了，
            // 并且是当前和下一个元素比较，所以只要到倒数第二个元素就行了。
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                //交换
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flag = false;
                }

                cnt++;
            }
            if (flag) break;
        }
        return array;
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] a = {87, 76, 65, 54, 43, 32, 21, 89, 98, 75, 36, 19, 40};//无序
        int[] a1 = {19,21,32,36,40,43,54,65,75,76,87,89,98};//有序
        int[] b = bubbleSort.sort(a);
        for (int i : b) {
            System.out.println(i);
        }
        System.out.println("运算次数："+cnt);
    }
}
