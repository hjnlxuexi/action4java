package hj.action.basics.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Title : 数组的性能优势</p>
 * <p>Description : </p>
 * <p>Date : 2018/12/22 </p>
 *
 * @author : hejie
 */
public class Performance {

    public static void main(String[] args) {
        Integer[] arrays = {1,2,3,4,5,6,7,8,9,0};
        int[] datas = new int[]{1,2,3,4,5,6};
        int sum = 0;
        /*
        Arrays.asList
        这里不能用datas，因为asList接受的参数是泛型的变长参数，
        基础类型的数组int[]不能泛型化，会被认为是一个参数
         */
        List list = Arrays.asList(arrays);//
        System.out.println(list.size());

        Long time1 = System.currentTimeMillis();
        for(int i = 0; i < 100000000 ; i++){
            sum += arrays[i%10];
        }
        Long time2 = System.currentTimeMillis();
        System.out.println("数组求和所花费时间：" + (time2 - time1) + "毫秒");


        Long time3 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            sum  += (int) list.get(i%10);
        }
        Long time4 = System.currentTimeMillis();
        System.out.println("List求和所花费时间：" + (time4 - time3) + "毫秒");
    }
}
