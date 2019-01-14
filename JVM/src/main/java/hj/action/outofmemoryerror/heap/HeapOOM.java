package hj.action.outofmemoryerror.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title : java堆区的内存溢出 OutOfMemoryError (OOM)</p>
 * <p>Description :
 *
 * 模拟设置虚拟机参数，VM Args：
 *      最小堆内存：-Xms 20m
 *      最大堆内存：-Xmx 20m
 *      dump出内存溢出时的堆快照：-XX:+HeapDumpOnOutOfMemoryError
 *
 *      当 -Xms = -Xmx 时，则是限制堆的扩展
 *
 * 分析：
 *      内存泄漏 （Memory leak），环状引用，导致无法被GC
 *      内存溢出 （Memory Overflow）
 *
 * </p>
 * <p>Date : 2018/12/30 </p>
 *
 * @author : hejie
 */
public class HeapOOM {

    static class OOMObject{}

    public static void main(String[] args) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();

        //模拟java堆内存溢出：java.lang.OutOfMemoryError: Java heap space
        while (true){
            list.add(new OOMObject());
        }

    }

}
