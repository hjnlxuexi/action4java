package hj.action.sort;

/**
 * <p>Title : 希尔排序</p>
 * <p>Description :
 * 1、选择希尔增量作为增量序列
 * 2、遍历增量序列，将原始数据，按当前增量分组
 * 3、每个分组进行插入排序
 * 4、直到增量值==1
 * </p>
 * <p>Date : 2018/12/17 </p>
 *
 * @author : hejie
 */
public class ShellSort implements Sort {
    private static int cnt  = 0;
    /**
     * 排序方法
     *
     * @param array 待排序数组
     * @return 排序结果
     */
    @Override
    public int[] sort(int[] array) {
        if (array == null || array.length == 0 || array.length == 1) {
            return array;
        }

        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            //以增量gap作为步长，进行分组的插入排序
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;

                boolean flag = true;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                    flag = false;
                    cnt++;
                }
                array[preIndex + gap] = temp;
                if (flag)cnt++;
            }
            gap /= 2;
        }
        return array;
    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        int[] a = {87, 76, 65, 54, 43, 32, 21, 89, 98, 75, 36, 19, 40};
        int[] a1 = {19,21,32,36,40,43,54,65,75,76,87,89,98};//有序
        int[] b = shellSort.sort(a);
        for (int i : b) {
            System.out.println(i);
        }

        System.out.println("运算次数："+cnt);
    }
}
