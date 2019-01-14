/**
 * <p>Title : AQS：队列同步器</p>
 * <p>Description :
 *
 * AQS = AbstractQueuedSynchronizer
 * 是重入锁、读写锁的基础框架。
 *
 * 原理：
 *  1、AQS使用一个int类型的成员变量state来表示"同步状态"，当state>0时表示已经获取了锁，当state=0时则表示释放了锁。
 *      通过get、set、compareAndSet三个方法来对同步状态state进行操作，并确保线程安全。
 *  2、AQS通过内置的FIFO同步队列（CLH）来实现多个线程间的派对工作，当某个线程获取同步状态（锁）失败，会构造一个(当前线程+等待状态信息）的节点加入到队尾，同时阻塞该线程，
 *      当同步状态state释放时唤醒线程，再次获取同步状态。
 *
 *
 *
 * </p>
 * <p>Date : 2019/1/3 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.locks.AQS;