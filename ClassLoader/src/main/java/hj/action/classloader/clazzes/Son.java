package hj.action.classloader.clazzes;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2018/12/6 </p>
 *
 * @author : hejie
 */
public class Son extends Father {
    static
    {
        System.out.println("儿子在静态代码块");
    }
    public Son()
    {
        System.out.println("我是儿子~");
    }
}
