/**
 * <p>Title : 重入锁</p>
 * <p>Description :
 *
 * 1、基于AQS，独占式（排它锁）
 *
 * 2、当前持有锁的线程优先，即当前线程已经占有了锁，又再次尝试获取锁，则优先拿到，冲入模式：在同步状态值 + 重入变量
 *
 * 3、公平锁，遵守AQS中CLH队列的FIFO特性，效率偏低
 *
 * 4、非公平锁，抢占，效率高
 *
 *
 *
 * ReentrantLock 与 Synchronized 的区别：
 *  1、ReentrantLock提供更丰富的功能，如：重入、超时锁、中断锁
 *  2、ReentrantLock提供了条件Condition，对线程的等待、唤醒操作更加详细和灵活，所以在多个条件变量和高度竞争锁的地方，ReentrantLock更加适合
 *  3、ReentrantLock提供了可轮询的锁请求。它会尝试着去获取锁，如果成功则继续，否则可以等到下次运行时处理，
 *      而synchronized则一旦进入锁请求要么成功要么阻塞，所以相比synchronized而言，ReentrantLock会不容易产生死锁
 *  4、ReentrantLock支持更加灵活的同步代码块，但是使用synchronized时，只能在同一个synchronized块结构中获取和释放。
 *      注：ReentrantLock的锁释放一定要在finally中处理，否则可能会产生严重的后果。
 *  5、ReentrantLock支持中断处理，且性能比synchronized高。
 *
 * </p>
 * <p>Date : 2019/1/5 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.locks.ReentrantLock;