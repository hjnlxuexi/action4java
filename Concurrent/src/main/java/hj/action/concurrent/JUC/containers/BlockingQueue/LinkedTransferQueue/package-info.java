/**
 * <p>Title : LinkedTransferQueue</p>
 * <p>Description :
 *
 * 起源：之前的BlockingQueue是对 读取 或者 写入 锁定整个队列，所以在比较繁忙的时候，各种锁比较耗时
 *  而当时有一个SynchronizedQueue其实不能叫Queue，因为只能放一个物件，要么有一个物件在等人拿，要么有一个空等人放
 *  根据这个原理，诞生了LinkedTransferQueue，利用CompareAndSwap进行一个无阻塞的队列，针对每一个操作进行处理样大家就不用抢得那么辛苦了
 *
 * LinkedTransferQueue：
 *  1、有容量
 *  2、不锁住整个队列
 *
 * 原理：
 *  预占模式：有就直接拿走，没有就占着这个位置直到拿到或者超时或者中断。
 *      即消费者线程到队列中取元素时，如果发现队列为空，则会生成一个null节点，然后park住等待生产者。
 *      后面如果生产者线程入队时发现有一个null元素节点，这时生产者就不会入列了，直接将元素填充到该节点上，唤醒该节点的线程，被唤醒的消费者线程拿东西走人。是不是有点儿SynchronousQueue的味道
 *
 *
 * 过程：
 *  1：Head->Data Input->Data
 *      Match: 根据他们的属性 发现 cannot match ，因为是同类的
 *      处理节点: 所以把新的data放在原来的data后面，然后head往后移一位，Reservation同理
 *      HEAD=DATA->DATA
 *  2：Head->Data Input->Reservation （取数据）
 *      Match: 成功match，就把Data的item变为reservation的值（null,有主了），并且返回数据。
 *      处理节点： 没动，head还在原地
 *      HEAD=DATA（用过）
 *  3：Head->Reservation Input->Data（放数据）
 *      Match: 成功match，就把Reservation的item变为Data的值（有主了），并且叫waiter来取
 *      处理节点： 没动
 *      HEAD=RESERVATION(用过)
 *
 * </p>
 * <p>Date : 2019-01-16 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.containers.BlockingQueue.LinkedTransferQueue;