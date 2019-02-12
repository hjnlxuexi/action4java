package hj.action.examples;

import java.util.LinkedList;

/**
 * <p>Title : </p>
 * <p>Description :
 * <p>
 * 给定一个整数字符串，从中扣掉k个数，保证剩下的整数是最小的。
 *
 * </p>
 * <p>Date : 2019-01-27 </p>
 *
 * @author : hejie
 */
public class KeepSmaller {

    /**
     * 吧问题拆解：
     * 1、k个数，把k==1 ；即：去掉一个数字使之最小
     * 2、循环k次
     *
     * @param number 原数字
     * @param k      去除数字的数量
     * @return 返回结果数字
     */
    private static String func1(String number, int k) {
        String dest = number;
        for (int i = 0; i < k; i++) {
            //去掉一个数字使之最小，如果 高位 > 下一位，则去掉
            StringBuilder rst = new StringBuilder();
            for (int j = 0; j < dest.length() - 1; j++) {
                char hight = dest.charAt(j);
                char low = dest.charAt(j + 1);
                if (hight > low) {
                    //去掉 j 对应的数值
                    rst.append(dest.substring(j + 1));
                    break;
                }
                rst.append(hight);
            }
            dest = rst.toString();
        }
        return dest;
    }

    /**
     * 第一种实现方式，时间复杂度：O(kn)
     *
     * 优化为：O(n)
     *
     * 用空间换时间：通过栈解决
     *
     * @param number
     * @param k
     * @return
     */
    private static String func2(String number, int k) {
        return number;
    }

    public static void main(String[] args) {
        String NUMBER = "73866745321239034836";
        String NUMBER1 = "123456789";
        String NUMBER2 = "9023456789";
        System.out.println(func1(NUMBER, 6));
        System.out.println(func2(NUMBER, 6));
    }
}
