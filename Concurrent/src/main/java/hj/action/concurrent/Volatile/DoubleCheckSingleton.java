package hj.action.concurrent.Volatile;

/**
 * <p>Title : 单例模式--双重检查锁DCL</p>
 * <p>Description :
 *
 * 为使得单例模式在多线程环境中有效，
 * 1、使用同步 + 检查 保证单例
 * 2、增加外层检查，提高效率
 * 3、需要通过volatile关键字，保障外层检查的 可见性、顺序性
 *
 * </p>
 * <p>Date : 2018/12/18 </p>
 *
 * @author : hejie
 */
public class DoubleCheckSingleton {
    //需要使用volatile   因为外部检查可能会有可见性、和new操作的顺序性问题
    private static volatile DoubleCheckSingleton dcs;

    private DoubleCheckSingleton (){}

    public static DoubleCheckSingleton getInstance() {
        //外部检查，避免同步锁，提高效率
        if (dcs == null){
            //同步块，保证单例
            synchronized (DoubleCheckSingleton.class){
                //内部检查，防止多线程重复new
                if (dcs == null) dcs = new DoubleCheckSingleton();
                /*
                new 创建对象：1、分配内存空间；2、初始化对象；3、将内存地址赋值给引用
                JMM可能对2、3两步进行重排序，所以会导致外部引用dcs误认为有值了，其实它只是一个空地址
                故需要用volatile 修饰 dcs，防止重排序
                 */
            }
        }
        return dcs;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20000; i++) {
            new Thread(() -> System.out.println(DoubleCheckSingleton.getInstance().hashCode()),"thread-"+i).start();
        }
    }
}
