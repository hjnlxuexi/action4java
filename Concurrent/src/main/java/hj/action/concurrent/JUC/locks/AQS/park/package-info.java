/**
 * <p>Title : 线程的阻塞与唤醒</p>
 * <p>Description :
 *
 * 阻塞线程：
 *    当线程获取锁失败，后需要将线程及同步信息添加到队列，并判断线程是否需要阻塞
 *    判断规则：
 *        1、如果当前线程的前驱节点状态为SIGNAL，则表明当前线程需要被阻塞，调用unpark()方法唤醒，直接返回true，当前线程阻塞
 *        2、如果当前线程的前驱节点状态为CANCELLED（ws > 0），则表明该线程的前驱节点已经等待超时或者被中断了，则需要从CLH队列中将该前驱节点删除掉，直到回溯到前驱节点状态 <= 0 ，返回false
 *        3、如果前驱节点非SIGNAL，非CANCELLED，则通过CAS的方式将其前驱节点设置为SIGNAL，返回false
 *    只有满足条件 1 时，通过LockSupport.park()阻塞线程。
 *
 *
 * 唤醒线程：
 *    1、当线程完成工作释放同步锁时，则唤醒下一个正常阻塞的线程；
 *    2、当前node.next有可能为空或者CANCELLED，所以需要从tail向上找到一个离当前node最近的正常节点
 *    3、通过LockSupport.unpark()唤醒找到的节点线程
 *
 *
 * LockSupport：
 *    用来创建锁和其他同步类的基本线程阻塞原语。
 *    park()：阻塞线程，但是只能调用一次，后来者会一直阻塞；
 *    unpark()：解除阻塞
 *
 *
 * </p>
 * <p>Date : 2019-01-07 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.locks.AQS.park;