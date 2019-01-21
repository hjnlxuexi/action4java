/**
 * <p>Title : CountDownLatch</p>
 * <p>Description :
 *
 * 用给定的计数 初始化 CountDownLatch。
 * 由于调用了 countDown() 方法，所以在当前计数到达零之前，await 方法会一直受阻塞。
 * 之后，会释放所有等待的线程，await 的所有后续调用都将立即返回。
 * 这种现象只出现一次————计数无法被重置。如果需要重置计数，请考虑使用 CyclicBarrier。
 *
 * 区别：
 *  1、CountDownLatch的作用是允许1或N个线程等待其他线程完成执行；而CyclicBarrier则是允许N个线程相互等待
 *  2、CountDownLatch的计数器无法被重置；CyclicBarrier的计数器可以被重置后使用，因此它被称为是循环的barrier
 *
 *
 * 原理：通过AQS共享式锁实现
 *  1、初始给定一个state值
 *  2、await() 获取锁，state>0时，一直获取失败，等待
 *  3、countDown()，释放锁，state==0 时，唤醒所有await的线程
 *
 * </p>
 * <p>Date : 2019-01-10 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.tools.CountDownLatch;