/**
 * <p>Title : AQS中维护的FIFO队列</p>
 * <p>Description :
 *
 * AQS中维护的FIFO队列：
 *  CLH = Craig, Landin, and Hagersten
 *
 *  1、一个FIFO队列
 *  2、当线程获取同步状态失败时，AQS会将当前线程以及等待状态等信息构造为一个节点，并加入到CLH队列
 *  3、加入到CLH的线程被阻塞；
 *  4、同步状态state释放时，头节点中的线程被唤醒（公平锁），然后该线程再次获取同步状态
 *
 * </p>
 * <p>Date : 2019/1/3 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.locks.AQS.CLH;