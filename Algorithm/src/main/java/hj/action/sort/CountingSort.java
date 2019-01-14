package hj.action.sort;

/**
 * <p>Title : 计数排序，开辟额外空间，非比较算法</p>
 * <p>Description :
 * 限制：
 * 计数排序要求输入的数据必须是有确定范围的整数。
 *
 * 步骤：
 * 1、原数组A中找出最大、最小元素，创建数组C，数组长度 = 最大值 - 最小值 +1
 * 2、统计A数组中每个值为i的元素，存入C的对应位置 C[i-最小值] = 次数
 * 3、按顺序将C里的值放到A中
 *
 * 结论：
 * 适合于数据较为紧凑的的整数排序，当排序数据跨度很大时，需要大量的时间和空间
 *
 * </p>
 * <p>Date : 2018/12/25 </p>
 *
 * @author : hejie
 */
public class CountingSort implements Sort {
    public static int cnt = 0;
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
        //1、找出最大的、最小的
        int min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min)
                min = array[i];
            if (array[i] > max)
                max = array[i];
        }

        //2、创建转换桶
        int[] C = new int[max-min+1];

        //3、将数据转换的桶中
        for (int i = 0; i < array.length; i++) {
            C[array[i] - min]++;  //计数
        }

        //4、将桶中的数据，放回Array
        int i = 0, index = 0;
        while (index<array.length){
            if (C[i] != 0){
                array[index] = i + min;
                C[i]--;
                index++;
            }else {
                i++;
            }
            cnt++;
        }
        return array;
    }


    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();
        int[] a = {87, 76, 65, 54, 43, 32, 21, 89, 98, 75, 36, 19, 40};
        int[] a1 = {19,21,32,36,40,43,54,65,75,76,87,89,98};//有序
        int[] b = countingSort.sort(a);
        for (int i : b) {
            System.out.print(i+",");
        }

        System.out.println("运算次数："+cnt);
    }
}
