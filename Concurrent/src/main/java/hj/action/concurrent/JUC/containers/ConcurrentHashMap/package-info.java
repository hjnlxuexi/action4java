/**
 * <p>Title : ConcurrentHashMap</p>
 * <p>Description :
 *
 * 背景：
 *  HashMap多线程环境下，put操作是有可能产生死循环的，导致CPU利用率接近100%。
 *  为了解决该问题，提供了Hashtable和Collections.synchronizedMap(hashMap)两种解决方案，
 *  但是这两种方案都是对读写加锁，独占式，一个线程在读时其他线程必须等待，吞吐量较低，性能较为低下。
 *
 * 在1.8版本以前，ConcurrentHashMap采用分段锁的概念，使锁更加细化。
 * 1.8已经改变了这种思路，而是利用CAS+Synchronized来保证并发更新的安全，当然底层采用数组+链表+红黑树的存储结构。
 *
 *
 * 1、table：用来存放Node节点数据的，默认为null，默认大小为16的数组，每次扩容时大小总是2的幂次方；
 * 2、nextTable：扩容时新生成的数据，数组为table的两倍；
 * 3、Node：节点，保存key-value的数据结构；内部类
 * 4、ForwardingNode：一个特殊的Node节点，hash值为-1，其中存储nextTable的引用。
 *      只有table发生扩容的时候，ForwardingNode才会发挥作用，作为一个占位符放在table中表示当前节点为null或则已经被移动
 * 5、sizeCtl：控制标识符，用来控制table初始化和扩容操作的，在不同的地方有不同的用途，其值也不同，所代表的含义也不同
 *      负数代表正在进行初始化或扩容操作；
 *      -1代表正在初始化；
 *      -N 表示有N-1个线程正在进行扩容操作；
 *      正数或0代表hash表还没有被初始化，这个数值表示初始化或下一次进行扩容的大小
 *
 *
 *
 *
 * 结构：table数组，短链表，红黑树
 *
 * 操作：put、get
 * 内部转换：扩容、链表与红黑树互转
 *
 * </p>
 * <p>Date : 2019-01-15 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.containers.ConcurrentHashMap;