package hj.action.concurrent.JMM.reOrder;

/**
 * <p>Title : 探索JMM重排序</p>
 * <p>Description : </p>
 * <p>Date : 2018/12/26 </p>
 *
 * @author : hejie
 */
public class ReOrderTest {
    static int a = 0;
    static boolean flag = false;

    /**
     * as-if-serial语义
     *
     * happens-before关系
     *
     * 在不改变程序执行结果的前提下，尽可能提高程序的运行效率。
     */
    public static void happensBefore() {
        int a = 1; //语句 A
        int b = 2; //语句 B
        int c = a + b; //语句 C

        /*
            由as-if-serial语义
            执行顺序：A->B->C   也可能  B->A->C    因为A、B相互比影响，C依赖A、B
         */

        /*
            happens-before关系:
              1、 A happens-before B
              2、 B happens-before C
              3、 A happens-before C

            1、2是程序顺序次序规则，3是传递性。
            但是，不是说通过重排序，B可能会排在A之前执行么，为何还会存在存在A happens-beforeB呢？
            这里再次申明A happens-before B不是A一定会在B之前执行，而是A的对B可见，但是相对于这个程序A的执行结果不需要对B可见，
            且他们重排序后不会影响结果，所以JMM不会认为这种重排序非法。

         */
    }

    /**
     * java异常对as-if-serial语义的特殊处理
     */
    public static void exception4AsIfSerial(){
        int a = 1;
        int b = 2;

        try {
            a = 3;           //A
            b = 1 / 0;       //B

            /*
                A 和 B 可能会发生重排序
             */
        } catch (Exception e) {

        } finally {
            //重排序之后   a=多少？
            System.out.println("a = " + a);
        }

        /*
        为了保证as-if-serial语义，Java异常处理机制对重排序做了一种特殊的处理：
            JIT在重排序时会在catch语句中插入错误代偿代码（a = 3）,这样做虽然会导致cathc里面的逻辑变得复杂，
        但是JIT优化原则是：尽可能地优化程序正常运行下的逻辑，哪怕以catch块逻辑变得复杂为代价。
         */
    }

    /**
     * 多线程环境下，as-if-serial语义是否起作用
     */
    public static void multiThread4AsIfSerial(){

        Thread A = new Thread(()-> {
            flag = true;
            a = 1;
            //本想造成这两条语句的重排序，但X86CPU不支持写写重排序
        });
        Thread B = new Thread(()->{
            if (flag){   //语句-1
                int i = a+a; //语句-2
                System.out.println("i="+i);
            }
        });

        A.start();
        B.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("a="+a+",flag="+flag);

    }

    public static void main(String[] args) {
        //exception4AsIfSerial();
        multiThread4AsIfSerial();
    }

}
