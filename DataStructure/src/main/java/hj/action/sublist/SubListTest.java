package hj.action.sublist;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title : subList有坑</p>
 * <p>Description :
 * 坑：
 *  1、subList返回的是原始list的一个视图，而不是全新的list对象
 *  2、subList操作会相互影响原list
 *  3、修改原list会产生fail-fast
 *
 * 使用场景：
 *  用subList处理局部列表，当在循环原列表的同时，修改列表
 *
 * </p>
 * <p>Date : 2018/12/24 </p>
 *
 * @author : hejie
 */
public class SubListTest {


    public static void main(String[] args) {
        //subList_1();
        subList_2();
    }

    public static void subList_1() {
        List<Integer> list_1 = new ArrayList<>();
        list_1.add(1);
        list_1.add(2);

        //通过构造函数，创建一个新的list，并将list_1的数据放到list_2中
        List<Integer> list_2 = new ArrayList<>(list_1);

        //想通过subList整一个，新的list
        List<Integer> list_3 = list_1.subList(0, list_1.size() - 1);
        //在list_3中加入一个元素
        list_3.add(3);

        System.out.println(list_3.size() + "->" + list_3);//期望两个元素:1,3
        System.out.println(list_1.size() + "->" + list_1);//期望两个元素:1,2，结果不正确？？？？？
        System.out.println(list_2.size() + "->" + list_2);//期望两个元素:1,2


        //通过list_1删除一个元素
        list_1.remove(1);
        System.out.println(list_3.size() + "->" + list_3);//期望两个元素:1,3，结果直接GG了
        System.out.println(list_1.size() + "->" + list_1);//期望一个元素:2
        System.out.println(list_2.size() + "->" + list_2);//期望两个元素:1,2

    }

    /**
     * 用subList处理局部列表，当在循环原列表的同时，修改列表
     */
    public static void subList_2() {
        //初始化一个列表
        int i = 0;
        List<Integer> list = new ArrayList<>(100);
        while (i != 100) {
            list.add(i++);
        }

        //想删掉第10-20的元素
        //for (int j = 0; j < list.size(); j++) {
        //    if (j >= 10 && j<20) {
        //        list.remove(j);
        //        //需要对j处理一下
        //    }
        //}


        //利用subList，一句话优雅解决
        list.subList(10, 20).clear();

        System.out.println(list);
    }
}
