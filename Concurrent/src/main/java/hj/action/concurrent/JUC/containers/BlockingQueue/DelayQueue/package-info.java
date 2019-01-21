/**
 * <p>Title : DelayQueue</p>
 * <p>Description :
 *
 * DelayQueue 是一个支持延时获取元素的无界阻塞队列。
 * 里面的元素全部都是“可延期”的元素，列头的元素是最先“到期”的元素，如果队列里面没有元素到期，是不能从列头获取元素的，哪怕有元素也不行。
 * 也就是说只有在延迟期到时才能够从队列中取元素。
 *
 * 应用场景：
 *  1、缓存：清掉缓存中超时的缓存数据
 *      缓存是有一定的时效性的，可以用DelayQueue保存缓存的有效期，然后利用一个线程查询DelayQueue，如果取到元素就证明该缓存已经失效了。
 *  2、任务超时处理
 *      DelayQueue保存当天将要执行的任务和执行时间，一旦取到元素（任务），就执行该任务。
 *
 * 要素：
 *  1、可重入锁ReentrantLock
 *  2、用于阻塞和通知的Condition对象
 *  3、根据Delay时间排序的优先级队列：PriorityQueue
 *  4、用于优化阻塞通知的线程元素leader
 *
 *
 * </p>
 * <p>Date : 2019-01-16 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.containers.BlockingQueue.DelayQueue;