/**
 * <p>Title : LinkedBlockingDeque</p>
 * <p>Description :
 *
 * 实现BlockingDeque接口，双向阻塞队列，而之前的BlockingQueue是单向的FIFO队列。
 * LinkedBlockingDeque则是一个由链表组成的双向阻塞队列，双向队列就意味着可以从对头、对尾两端插入和移除元素，
 * 同样意味着LinkedBlockingDeque支持FIFO、FILO两种操作方式。
 *
 *
 * 提供以First、Last结尾的API方法来实现双端操作
 *
 * LinkedBlockingDeque底层实现机制与LinkedBlockingQueue一样，依然是通过互斥锁ReentrantLock 来实现，
 * 两个Condition：notEmpty、notFull 做协调生产者、消费者问题。
 *
 * </p>
 * <p>Date : 2019-01-16 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.containers.BlockingQueue.LinkedBlockingDeque;