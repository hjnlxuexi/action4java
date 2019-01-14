package hj.action.sort;

/**
 * <p>Title : 快速排序</p>
 * <p>Description :
 * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * </p>
 * <p>Date : 2018/12/17 </p>
 *
 * @author : hejie
 */
public class QuickSort implements Sort {
    private static int cnt = 0;

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

        quickSort(array, 0, array.length - 1);

        return array;
    }

    /**
     * 选举基准元素，小于基准的元素放到左边，否则放到右边
     *
     * @param array 原始数组
     * @param left  坐指针
     * @param right 右指针
     */
    private static void quickSort(int[] array, int left, int right) {
        if (left < 0 || left >= right) return;
        int l = left;
        int r = right;

        //选择基准，可以采用其他方式选择，如：随机
        int pivot = array[l];

        //left与right重合判断
        while (l < r) {
            //1、从right位置开始，右向左找第一个小于pivot的值
            while (l < r && array[r] >= pivot) r--;
            //2、发现小于pivot的值，将其放到left位置，并将left指针右移
            if (l < r) {
                array[l] = array[r];
                l++;
            }

            //3、从left位置开始，左到右找到第一个大于pivot的值
            while (l < r && array[l] < pivot) l++;
            //4、找到大于pivot的值，将其放到right的位置，并将right指针左移
            if (l < r) {
                array[r] = array[l];
                r--;
            }

            cnt++;
        }
        //产生left与right碰撞，left = right ，此为pivot的位置
        array[l] = pivot;
        //递归 左右俩边快速排序
        quickSort(array, left, l - 1);
        quickSort(array, l + 1, right);
    }


    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] a = {87, 76, 65, 54, 43, 32, 21, 89, 98, 75, 36, 19, 40};
        int[] a1 = {19,21,32,36,40,43,54,65,75,76,87,89,98};//有序
        int[] b = quickSort.sort(a);
        for (int i : b) {
            System.out.print(i+",");
        }

        System.out.println("运算次数："+cnt);
    }
}
