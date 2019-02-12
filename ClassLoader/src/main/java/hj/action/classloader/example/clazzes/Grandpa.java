package hj.action.classloader.example.clazzes;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2018/12/6 </p>
 *
 * @author : hejie
 */
public class Grandpa {
    static
    {
        System.out.println("爷爷在静态代码块");
    }
    public static int age = 50;
    public Grandpa(){
        System.out.println("我是爷爷~");
    }
}
