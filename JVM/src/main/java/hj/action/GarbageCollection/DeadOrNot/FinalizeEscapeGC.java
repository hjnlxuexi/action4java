package hj.action.GarbageCollection.DeadOrNot;

/**
 * <p>Title : 通过finalize逃脱GC的回收</p>
 * <p>Description :
 *
 * 对象在finalize()中拯救自己，需要重新将自己与一个引用连接上。
 *
 * </p>
 * <p>Date : 2019-01-05 </p>
 *
 * @author : hejie
 */
public class FinalizeEscapeGC {

    //保留一个安全钩子，用于在finalize时连接
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes,i am stile alive :)");
    }

    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed !");
        //拯救即将配回收的自己
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        //分配一个对象
        SAVE_HOOK = new FinalizeEscapeGC();

        //第一次
        check(1);
        //第一次： 对象的finalize方法都只会被系统自动调用一次
        check(2);

    }

    private static void check(int cnt) throws InterruptedException {
        System.out.println();
        System.out.println("the "+cnt +" times");
        //断开引用，然后GC
        SAVE_HOOK = null;
        System.gc();

        //由于finalize方法优先级很低，暂停0.5秒，看是否执行了finalize，重新为SAVE_HOOK连接了对象
        Thread.sleep(500);
        if (SAVE_HOOK != null)
            SAVE_HOOK.isAlive();
        else
            System.out.println("no, i am dead :(");

    }
}
