/**
 * <p>Title : locks里的Condition</p>
 * <p>Description :
 *
 * 目的：
 *  用于解决使用Object的wait()、notify()等待/通知模式，来实现的同步控制时局限性。
 *
 *
 * Condition提供的更丰富的方式来 阻塞/唤醒 线程
 *  1、await()：使得当前线程处于等待状态，直到收到唤醒信号或被中断；
 *  2、await(long time, TimeUnit unit)：使得当前线程处于等待状态，直到达到规定的时间、收到唤醒信号、或被中断；
 *  3、awaitNanos(long nanosTimeout)：返回值表示，还剩多少纳秒被唤醒；
 *  4、awaitUninterruptibly()：线程收到唤醒信号之前一直等待，不响应中断；
 *  5、awaitUntil(Date deadline)：线程处于等待，直到收到信号、被中断、或到达时间，返回值：未达到时间-true，达到时间-false
 *  6、signal()：唤醒一个等待线程，该线程从等待方法返回前必须获得与Condition相关的锁；至于唤醒哪一个线程，由具体的实现决定
 *  7、signalAll()：唤醒所有等待线程
 *
 *
 * Condition自己维护自己的队列，条件队列
 *
 * 等待：
 *  1、当前线程新建一个节点并加入到条件队列；
 *  2、释放当前线程的所有同步状态(清零重入的值)；
 *  3、不断检测该节点代表的线程是否出现在CLH同步队列中（收到signal信号之后就会在AQS队列中检测到），如果不存在则一直挂起，否则参与竞争同步状态。
 *
 * 通知：
 *  1、判断当前线程是否已获得锁，未获得锁则抛出异常，因为只有当前获得锁的线程才能通知其他线程；
 *  2、将唤醒条件队列的首节点移动到AQS的CLH队列中；
 *  3、唤醒
 *
 *
 * </p>
 * <p>Date : 2019-01-06 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.locks.Condition;