package hj.action.outofmemoryerror.methodArea;

/**
 * <p>Title : 方法区内存溢出</p>
 * <p>Description :
 *
 * 基于动态加载类，填满方法区内存:
 *  1、java API：反射GeneratedConstructorAccessor 或 动态代理
 *  2、框架：CGLib （Spring等 也基于此）
 *  3、语言：Groovy等
 *
 *
 *  大小设置：（jdk1.6及之前，才生效）
 *      -XX:PermSize
 *      -XX:MaxPermSize
 *
 *  产生异常：java.lang.OutOfMemoryError: PermGen space
 *
 * </p>
 * <p>Date : 2018/12/30 </p>
 *
 * @author : hejie
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {
    }
}
