package hj.action.basics.String;

/**
 * <p>Title : 测试String对象的并发性</p>
 * <p>Description :
 *
 *  虽然String是final的，并且的所有操作都是new新的String对象，
 *  但从使用者的角度讲，不关心结果是原对象还是new的新对象。
 *  在多线程环境中String的所有方法都不能保证数据的一致性，
 *  所以，String必然是线程不安全的。
 * </p>
 * <p>Date : 2018/12/21 </p>
 *
 * @author : hejie
 */
public class Concurrency {

    public static void main(String[] args) {

        String s = "1234567890";
        Thread t1 = new Thread(()->{
            System.out.println(s.substring(4));
        });
        Thread t2 = new Thread(()->{
            //s = "097654321";
        });

        t1.start();
        t2.start();
    }
}
