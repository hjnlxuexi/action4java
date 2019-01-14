/**
 * <p>Title : 读写锁</p>
 * <p>Description :
 *
 * ReentrantReadWriteLock：
 *      1、应用场景，在读多写少的情况下，独占式的重入锁(ReentrantLock)是不合适的，会导致读性能的降低；
 *      2、结构，一个锁对象sync。读写分离，提高并发性
 *          在同一时间可以允许多个读线程同时访问，但是在写线程访问时，所有读线程和写线程都会被阻塞
 *
 * 特性：
 *  1、公平性：支持公平锁和费公平锁
 *  2、重入性：支持重入，读锁和写锁分别都支持重入
 *  3、锁降级：写锁能够降级为读锁
 *
 *
 * 只有一个锁对象：Sync
 *  一个同步状态：state  int 32位
 *      按位切割使用：前16位表示读，后16位表示写
 *      通过位运算确定是读锁还是写锁：
 *          isWriteLocked()：
 *              static final int SHARED_SHIFT   = 16;
 *              static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
 *              static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
 *              static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
 *              static int sharedCount(int c){return c >>> SHARED_SHIFT;}
 *              static int exclusiveCount(int c){return c & EXCLUSIVE_MASK;}
 *      写锁优先，当 exclusiveCount!=0 时  就是写锁
 *
 *
 * 写锁：独占锁
 *  获取锁：
 *      1、等待读锁全部释放后才能获取到写锁；
 *      2、一旦获取到写锁，其他试图获取读锁、写锁的线程都会被阻塞
 *  释放锁：
 *      减少可重入的同步状态state
 *
 *
 * 读锁：共享锁
 *  获取锁：
 *      1、存在写锁 && 持有写锁的线程不是自己，则需要阻塞等待
 *      2、HoldCounter 每个线程的读锁数量，配合ThreadLocalHoldCounter 与线程绑定
 *
 *
 * 锁降级：线程由写锁降级为读锁
 *  1、线程先获取写锁
 *  2、线程然后获取读锁
 *  3、线程再释放写锁
 *
 *
 * </p>
 * <p>Date : 2019-01-06 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.locks.ReentrantReadWriteLock;