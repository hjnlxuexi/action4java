/**
 * <p>Title : AQS同步状态的获取与释放</p>
 * <p>Description :
 *
 * <P>独占式：</p>同一时刻仅有一个线程持有同步状态。
 *  在AQS中维护着一个FIFO的同步队列，当线程获取同步状态失败后，则会加入到这个CLH同步队列的对尾并一直保持着自旋。
 *  在CLH同步队列中的线程在自旋时会判断其前驱节点是否为首节点，如果为首节点则不断尝试获取同步状态，获取成功则退出CLH同步队列。
 *  当线程执行完逻辑后，会释放同步状态，释放后会唤醒其后继节点。
 *
 *      acquire(int arg)： 独占式获取同步状态
 *          线程中断不敏感：由于线程获取同步状态失败加入到CLH同步队列中，后续对线程进行中断操作时，线程不会从同步队列中移除。
 *          1、尝试获取锁，tryAcquire()
 *          2、获取失败则加入到CLH，addWaiter(Node.EXCLUSIVE)
 *          3、阻塞当前线程，自旋，不断尝试获取锁，直到获取锁成功，acquireQueued()，返回在等待中是否被中断，但这个中断的结果被忽略
 *          4、拿到锁之后，产生一个中断，selfInterrupt()
 *
 *      acquireInterruptibly(int arg)：独占式获取同步状态，响应中断
 *          但与acquire()的差别在于，它是响应中断的，当线程发生中断时，直接抛出异常。
 *
 *      tryAcquireNanos(int arg,long nanos)：独占式获取同步状态，响应中断，超时控制
 *          在acquireInterruptibly()的基础上，增加超时时间(纳秒)，
 *          即如果当前线程没有在指定时间内获取同步状态，则会返回false，否则返回true。
 *          1、当线程第一次获取同步锁失败，则计算出超时间隔的纳秒数：nanosTimeout = deadline - System.nanoTime();
 *          2、nanosTimeout <= 0 表示超时，直接返回false，获取锁失败
 *          3、nanosTimeout > spinForTimeoutThreshold （1毫秒）时，则休眠nanosTimeout纳秒
 *          4、0 < nanosTimeout <= spinForTimeoutThreshold 则直接进行"自旋"
 *
 *      release(int arg)：独占式同步锁释放
 *          1、tryRelease(int arg) 尝试释放锁
 *          2、unparkSuccessor(Node node) 唤醒下一个节点
 *
 * --------------------------------------------------------------------------------------------------------------------
 *
 * <p>共享式：</p>同一时刻可以有多个线程获取同步状态。
 *
 *      acquireShared(int arg)：共享式获取同步状态
 *          1、tryAcquireShared(int arg) 尝试获取同步状态，<0 则表示获取失败
 *          2、获取失败则调用，doAcquireShared(int arg)，addWaiter(Node.SHARED)添加节点到CLH，
 *          3、自旋，获取同步状态，直到 tryAcquireShared(int arg) >= 0
 *
 *      acquireSharedInterruptibly(int arg)：共享式获取同步状态，响应中断
 *
 *      tryAcquireSharedNanos(int arg, long nanosTimeout)：共享式获取同步锁，响应中断，超时控制
 *
 *      releaseShared(int arg)：共享式释放同步锁
 *          可能存在多个线程同时释放同步状态锁，为确保同步状态安全的释放，通过自旋和CAS实现
 *
 *
 * </p>
 * <p>Date : 2019/1/3 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.locks.AQS.state;