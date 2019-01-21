/**
 * <p>Title : 同步辅助类CyclicBarrier</p>
 * <p>Description :
 *
 * 功能：让一组线程到达一个屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 * 通过重入锁 ReentrantLock + Condition 实现
 *
 * await(): 线程会一直等待，除非发生一下情况：
 *  1、最后一个线程到达
 *  2、超时
 *  3、当前等待线程中断
 *  4、另一个等待线程中断
 *  5、其他线程等待barrier超时
 *  6、调用reset()
 *
 * 应用场景：
 *      CyclicBarrier适用于多线程结果合并的操作，用于多线程计算数据，最后合并计算结果的应用场景
 *
 *
 * 与CountDownLaunch的区别：
 *  CountDownLaunch是工作线程只帮助计数，不会被阻断；外部线程被阻断，直到计数器够数
 *  CyclicBarrier是阻断工作线程，直到被阻断的工作线程够数，最后到达的线程执行外屏障工作，再唤醒所有的工作线程
 *
 * </p>
 * <p>Date : 2019-01-09 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.tools.CyclicBarrier;