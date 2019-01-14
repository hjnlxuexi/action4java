package hj.action.outofmemoryerror.stack;

/**
 * <p>Title : 栈内存溢出：OutOfMemoryError</p>
 * <p>Description :
 *
 *  原理：
 *      JVM能使用的总内存相对是固定的：堆 + 栈 + 方法区 + 程序计数器等
 *      栈是某个线程独有的，当多线程环境下，允许的栈空间的越大，则能支持的线程数就会越小
 *      当无法再为某个线程分配更多的栈空间时，或者不能再创建线程时，则会抛出异常：OutOfMemoryError
 *
 * </p>
 * <p>Date : 2018/12/30 </p>
 *
 * @author : hejie
 */
public class StackOOM {

    private void run(){
        while (true){}
    }

    private void stackLeakByMultiThread(){
        while (true){
            Thread t = new Thread(this::run);
            t.start();
        }

    }
    public static void main(String[] args) {
        StackOOM stackOOM = new StackOOM();
        stackOOM.stackLeakByMultiThread();
        //异常：java.lang.OutOfMemoryError: unable to create new native thread
    }
}
