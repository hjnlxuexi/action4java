/**
 * <p>Title : ConcurrentLinkedQueue：通过链表实现的并发队列</p>
 * <p>Description :
 * http://cmsblogs.com/?p=2353
 *
 * 要实现一个线程安全的队列有两种方式：阻塞和非阻塞。阻塞队列无非就是锁的应用，而非阻塞则是CAS算法的应用。
 *
 * ConcurrentLinkedQueue是一个基于链接节点的无边界的线程安全队列，它采用FIFO原则对元素进行排序。
 * 采用“wait-free”算法（即CAS算法）来实现的。
 *
 * 不变性特征：
 *  1、最后一个元素(tail)的next为null
 *  2、队列中所有未删除的节点的item都不能为null且都能从head节点遍历到
 *  3、对于要删除的节点，不是直接将其设置为null，而是先将其item域设置为null（迭代器会跳过item为null的节点）
 *  4、允许head和tail更新滞后。这是什么意思呢？意思就说是head、tail不总是指向第一个元素和最后一个元素
 *
 *
 * head：
 *      不变性：
 *          1、所有未删除的节点都可以通过head节点遍历到
 *          2、head不能为null
 *          3、head节点的next不能指向自身
 *      可变性：
 *          1、head的item可能为null，也可能不为null
 *          2、允许tail滞后head，也就是说调用succ()方法，从head不可达tail
 *
 * tail：
 *      不变性：
 *          1、tail不能为null
 *      可变性：
 *          1、tail的item可能为null，也可能不为null
 *          2、tail节点的next域可以指向自身
 *          3、允许tail滞后head，也就是说调用succ()方法，从head不可达tail
 *
 * </p>
 * <p>Date : 2019-01-15 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.containers.ConcurrentLinkedQueue;