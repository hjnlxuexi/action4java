package hj.action.classloader;

/**
 * <p>Title : </p>
 * <p>Description :
 * 分清概念：
 *  1、类构造器：顺序执行静态代码
 *      1.1、给静态成员变量赋值
 *      1.2、执行静态代码块
 *  2、对象构造器：对象实例化 new
 *      2.1、先收集成员变量赋值
 *      2.2、然后收集执行普通代码块
 *
 * </p>
 * <p>Date : 2018/12/6 </p>
 *
 * @author : hejie
 */
public class Book {

    public static void main(String[] args) {
        System.out.println("开始...");
        staticFunction();
        System.out.println("结束...");
    }
    static Book book = new Book();
    static {
        System.out.println("书的静态代码块");
    }
    {
        System.out.println("书的普通代码块");
    }
    Book(){
        System.out.println("书的构造方法");
        System.out.println("price=" + price +",amount=" + amount);
    }
    public static void staticFunction(){
        System.out.println("书的静态方法");
        System.out.println("amount=" + amount);
    }
    int price = 110;
    static int amount = 112;

}
