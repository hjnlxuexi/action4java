/**
 * <p>Title : ArrayBlockingQueue：基于数组实现的阻塞队列</p>
 * <p>Description :
 *
 * ArrayBlockingQueue为有界且固定，其大小在构造时由构造函数来决定，确认之后就不能再改变了。固定容量
 * ArrayBlockingQueue支持对等待的生产者线程和使用者线程进行排序的可选公平策略，
 *  但是在默认情况下不保证线程公平的访问，在构造时可以选择公平策略（fair = true）。
 *  公平性通常会降低吞吐量，但是减少了可变性和避免了“不平衡性”。
 *
 *
 * 内部实现：ReentrantLock + Condition 来完成多线程环境的并发操作
 *  1、items，一个定长数组，维护ArrayBlockingQueue的元素，Object[]
 *  2、takeIndex，int，为ArrayBlockingQueue队首位置
 *  3、putIndex，int，ArrayBlockingQueue对尾位置
 *  4、count，元素个数
 *  5、lock，锁，ArrayBlockingQueue出列入列都必须获取该锁，两个步骤公用一个锁 ； ReentrantLock
 *  6、notEmpty，出列条件；Condition
 *  7、notFull，入列条件
 *
 * </p>
 * <p>Date : 2019-01-15 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.containers.BlockingQueue.ArrayBlockingQueue;