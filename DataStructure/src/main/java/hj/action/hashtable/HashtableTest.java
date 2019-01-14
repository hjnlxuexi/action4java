package hj.action.hashtable;

import java.util.Hashtable;

/**
 * <p>Title : Hashtable</p>
 * <p>Description :
 *
 * 用法上与HashMap很类似，主要区别在于：
 * 1、HashMap可以存放值为null的 key、value，而Hashtable不行；
 * 2、Hashtable是synchronized，线程安全，HashMap是非线程安全的；
 * 3、最新HashMap的存储结构为：数组+红黑树(或短链表)，Hashtable为：数组+单向链表，读写性能不佳；
 *
 *
 * 官方建议：
 * 如果不需要线程安全的实现，建议使用HashMap代替Hashtable。
 * 如果需要线程安全的高并发实现，那么建议使用ConcurrentHashMap代替Hashtable。
 *
 *
 * </p>
 * <p>Date : 2018/12/22 </p>
 *
 * @author : hejie
 */
public class HashtableTest {

    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();

        hashtable.put("key1",1);
        //Hashtable的key和value都不允许为null
        //而 HashMap的key和value可以为null，key==null时采用putForNullKey处理
        hashtable.put("null",null);
        hashtable.put(null,"null");

        System.out.println(hashtable);
    }

}
