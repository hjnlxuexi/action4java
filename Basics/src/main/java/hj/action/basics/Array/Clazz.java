package hj.action.basics.Array;

/**
 * <p>Title : 数组到底是个什么样的类？</p>
 * <p>Description : </p>
 * <p>Date : 2018/12/22 </p>
 *
 * @author : hejie
 */
public class Clazz {
    public static void main(String[] args) {
        int[] array = new int[10];
        int[] a = {1,2,3};
        int[][] ab = new int[2][2];
        int[][][] abc = new int[2][2][2];

        System.out.println("父类是谁："+array.getClass().getSuperclass());
        System.out.println("array是谁："+array.getClass().getName());
        System.out.println("a是谁："+a.getClass().getName());
        System.out.println("ab是谁："+ab.getClass().getName());
        System.out.println("abc是谁："+abc.getClass().getName());

        System.out.println(Object.class);
        System.out.println(Object[].class);
        System.out.println(Object[][].class);

        System.out.println(Integer[].class);
        System.out.println((new Integer[10]).getClass().getName());

        System.out.println(int[].class);
        System.out.println((new int[10]).getClass().getName());


        Class clazz = array.getClass();
        System.out.println(clazz.getDeclaredFields().length);//没有定义成员变量
        System.out.println(clazz.getDeclaredMethods().length);//没定义有方法
        System.out.println(clazz.getDeclaredConstructors().length);//没有定义构造函数
        System.out.println(clazz.getDeclaredAnnotations().length);//没有定义注解
        System.out.println(clazz.getDeclaredClasses().length);

        //那为啥会有length这个成员变量呢？
        System.out.println(array.length);
        //通过 javap -c 查看类信息，
        //0 iconst_2                   //将int型常量2压入操作数栈
        //1 newarray 10 (int)          //将2弹出操作数栈，作为长度，创建一个元素类型为int, 维度为1的数组，并将数组的引用压入操作数栈
        //3 astore_1                   //将数组的引用从操作数栈中弹出，保存在索引为1的局部变量(即a)中
        //4 aload_1                    //将索引为1的局部变量(即a)压入操作数栈
        //5 arraylength                //从操作数栈弹出数组引用(即a)，并获取其长度(JVM负责实现如何获取)，并将长度压入操作数栈
        //6 istore_2                   //将数组长度从操作数栈弹出，保存在索引为2的局部变量(即i)中
        //7 return                     //main方法返回
        // JVM对数组的长度做了特殊的处理，它是通过arraylength这条指令来实现的。

    }
}
