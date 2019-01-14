package hj.action.sort;

/**
 * <p>Title : 插入排序</p>
 * <p>Description :
 * 1、从第一个元素开始，该元素可以认为已经被排序；
 * 2、取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 3、如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 4、重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 5、将新元素插入到该位置后；
 * 6、重复步骤2~5
 * </p>
 * <p>Date : 2018/12/17 </p>
 *
 * @author : hejie
 */
public class InsertionSort implements Sort {
    //
    private static int cnt = 0;
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

        for (int i = 0; i < array.length-1; i++) {
            int current = i;
            int next = array[i+1];
            //遍历已经排好序的数组，找到下一个元素的位置
            boolean flag = true;
            while (current>=0 && next<array[current]){
                //将队尾的元素往后移一个
                array[current+1] = array[current];
                //往前移一个位置
                current--;

                flag = false;
                cnt++;
            }
            //将元素放到对应的位置
            array[current+1] = next;
            if (flag)cnt++;
        }

        return array;
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] a = {87, 76, 65, 54, 43, 32, 21, 89, 98, 75, 36, 19, 40};
        int[] a1 = {19,21,32,36,40,43,54,65,75,76,87,89,98};//有序
        int[] b = insertionSort.sort(a);
        for (int i : b) {
            System.out.println(i);
        }

        System.out.println("运算次数："+cnt);
    }
}
