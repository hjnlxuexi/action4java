package hj.action.equals;

/**
 * <p>Title : 按常理推断equals与compareTo</p>
 * <p>Description :
 *
 *  compareTo是判断元素在排序中的位置是否相等，equals是判断元素是否相等，既然一个决定排序位置，一个决定相等，所以我们非常有必要确保当排序位置相同时，其equals也应该相等。
 *
 *  如：
 *      list.indexOf(obj);
 *      Collections.binarySearch(list, obj);
 *  1、indexOf是基于equals来实现的;
 *  2、binarySearch是基于compareTo方法的
 *  所以对象的equals 与 compareTo 实现不一致，则会出现于期望的记过不一致
 *
 * </p>
 * <p>Date : 2018/12/25 </p>
 *
 * @author : hejie
 */
public class EqualsAndCompare {
}
