package hj.action.outofmemoryerror.stack;

/**
 * <p>Title : 栈溢出：StackOverflowError</p>
 * <p>Description :
 *
 * 调整栈空间大小：-Xss 160k
 *
 *
 * 结论：
 *  单线程环境下，无论是栈帧太大还是虚拟机栈容量太小，只要出现内存无法分配了，异常就是：StackOverflowError
 *
 * </p>
 * <p>Date : 2018/12/30 </p>
 *
 * @author : hejie
 */
public class StackSOF {
    private int stackLen = 1;

    public void stackLeak() {
        stackLen++;
        stackLeak();//递归增加栈深度
    }

    public static void main(String[] args) {
        StackSOF stackSOF = new StackSOF();
        try {
            stackSOF.stackLeak();
        }catch (Throwable e){
            System.out.println(stackSOF.stackLen);
            throw e;
        }
    }
}
