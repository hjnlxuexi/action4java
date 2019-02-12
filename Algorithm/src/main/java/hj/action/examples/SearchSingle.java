package hj.action.examples;

/**
 * <p>Title : 寻找单独的元素</p>
 * <p>Description :
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 要求：
 * 1、线性时间复杂度 O(n)
 * 2、不使用额外空间 O(0)
 * <p>
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 *
 * 答案：
 *  采用位运算：异或。两个bit不相等则为1，否则为0
 *
 *
 * </p>
 * <p>Date : 2019-01-26 </p>
 *
 * @author : hejie
 */
public class SearchSingle {

    /**
     * 元素值
     *
     * @param a 数组
     * @return  单独的元素
     */
    private static int searchSingle(int[] a) {
        int e = 0;
        for (int i: a) {
            e = e ^ i;
        }
        return e;
    }

    public static void main(String[] args) {
        int[] a = {7,7,4, 1, 3, 1, 3, 2, 2, 13,47,13,47};
        int r = searchSingle(a);
        System.out.println(r);

    }
}
