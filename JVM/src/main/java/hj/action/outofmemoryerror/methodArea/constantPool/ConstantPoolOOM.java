package hj.action.outofmemoryerror.methodArea.constantPool;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title : 常量池内存溢出</p>
 * <p>Description :
 *
 * 1、常量池是JVM方法区的一部分
 * 2、jdk1.7之前，常量池分配在"永久代(PermGen)"；
 * 3、可以通过：-XX:PermSize 和 -XX:MaxPermSize ，限制方法区的大小，从而间接限制常量池的大小
 *
 * 4、jdk1.7之后，常量池存放引用，jdk1.7之前是存放常量本身(副本)
 *
 *
 * String.intern(): 先找常量池，找到了就返回常量池字符串对象，没找到在将其放到常量池
 *
 * </p>
 * <p>Date : 2018/12/30 </p>
 *
 * @author : hejie
 */
public class ConstantPoolOOM {

    public static void permGen(){
        //在jdk1.6或以下版本中，以下参数才有效
        //-XX:PermSize=10M -XX:MaxPermSize=10M
        //会产生异常：java.lang.OutOfMemoryError: PermGen space
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true){
            //操作常量池
            list.add(String.valueOf(i).intern());
        }
    }

    /**
     * jdk1.6 输出两个false
     * jdk1.7 输出true，false
     */
    public static void stringIntern(){
        String s1 = new StringBuilder().append("Soft").append("ware").toString();//new 在堆区创建String对象
        System.out.println( s1.intern() == s1 );

        String s2 = new StringBuilder().append("ja").append("va").toString();//"java" 在常量池中已存在它的引用
        System.out.println( s2.intern() == s2 );
    }
    public static void main(String[] args) {
        //permGen();
        stringIntern();
    }
}
