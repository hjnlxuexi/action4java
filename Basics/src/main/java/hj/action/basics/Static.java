package hj.action.basics;

/**
 * <p>Title : 研究一下static关键字</p>
 * <p>Description :
 * 所属者是类本身，其他的则属于实例对象
 *
 * </p>
 * <p>Date : 2018/12/21 </p>
 *
 * @author : hejie
 */
public class Static {
    private static Integer i = 0;

    public static void main(String[] args) {
        Static s1 = new Static();
        Static s2 = new Static();

        Static.i++;
        s1.i++;
        s2.i++;
        System.out.println(Static.i);
        System.out.println("s1:"+s1.i);
        System.out.println("s2:"+s2.i);
    }
}
