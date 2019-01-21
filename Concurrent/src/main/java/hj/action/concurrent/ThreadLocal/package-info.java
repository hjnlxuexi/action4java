/**
 * <p>Title : ThreadLocal</p>
 * <p>Description :
 *
 * ThreadLocal与线程同步机制不同，线程同步机制是多个线程共享同一个变量，
 * 而ThreadLocal是为每一个线程创建一个单独的变量副本，故而每个线程都可以独立地改变自己所拥有的变量副本，而不会影响其他线程所对应的副本。
 * 可以说ThreadLocal为多线程环境下变量问题提供了另外一种解决思路。
 *
 *
 * get()：返回此线程局部变量的当前线程副本中的值。
 * initialValue()：返回此线程局部变量的当前线程的“初始值”。
 * remove()：移除此线程局部变量当前线程的值。
 * set(T value)：将此线程局部变量的当前线程副本中的值设置为指定值。
 *
 * 注意：
 *  ThreadLocal实例本身是不存储值，它只是提供了一个在当前线程中找到副本值的key。
 *  是ThreadLocal包含在Thread中，而不是Thread包含在ThreadLocal中，常常会弄错他们的关系。
 *
 *
 * 问题思考：
 * https://www.jianshu.com/p/ee8c9dccc953
 * 1、ThreadLocal让访问某个变量的线程都拥有自己的局部变量，但是如果这个局部变量都指向同一个对象呢？这个时候ThreadLocal就失效了。
 * 2、在JAVA里面，存在强引用、弱引用、软引用、虚引用。这里主要谈一下强引用和弱引用。
 *  ThreadLocal在ThreadLocalMap中作为Entry的key是弱引用，是会配GC回收的，但Entry中的value是强引用，
 *  所以ThreadLocal有内存泄露的可能性，尤其是使用线程池的时候
 *
 *
 * 总结：
 * ThreadLocal 不是用于解决共享变量的问题的，也不是为了协调线程同步而存在，而是为了方便每个线程处理自己的状态而引入的一个机制。这点至关重要。
 * 每个Thread内部都有一个ThreadLocal.ThreadLocalMap类型的成员变量，该成员变量用来存储实际的ThreadLocal变量副本。
 * ThreadLocal并不是为线程保存对象的副本，它仅仅只起到一个索引的作用。它的主要目的是为每一个线程隔离一个类的实例，这个实例的作用范围仅限于线程内部。
 *
 * </p>
 * <p>Date : 2019-01-17 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.ThreadLocal;