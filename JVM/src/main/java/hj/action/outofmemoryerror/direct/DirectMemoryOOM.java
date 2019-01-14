package hj.action.outofmemoryerror.direct;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * <p>Title : 直接内存溢出</p>
 * <p>Description :
 * JVM管理外的内存
 *
 * 设置：
 *  -XX:MaxDirectMemorySize，默认等于heap堆（-Xmx）的大小
 *
 *  本例：-Xmx20m -XX:MaxDirectMemorySize=10m
 *
 * </p>
 * <p>Date : 2018/12/30 </p>
 *
 * @author : hejie
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
