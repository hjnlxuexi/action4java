/**
 * <p>Title : PriorityBlockingQueue</p>
 * <p>Description :
 *
 * PriorityBlockingQueue是一个支持优先级的无界阻塞队列。
 * 默认情况下元素采用自然顺序升序排序，当然我们也可以通过构造函数来指定Comparator来对元素进行排序。
 * 需要注意的是PriorityBlockingQueue不能保证同优先级元素的顺序。
 *
 * 底层结构：二叉堆
 *      树结构性：完全二叉树
 *      堆序性：父节点的键值总是保持固定的序关系于任何一个子节点的键值，且每个节点的左子树和右子树都是一个二叉堆。它有两种表现形式：最大堆、最小堆。
 *          最大堆：父节点的键值总是大于或等于任何一个子节点的键值
 *          最小堆：父节点的键值总是小于或等于任何一个子节点的键值
 *      二叉堆一般用数组表示，如果父节点的节点位置在n处，那么其左孩子节点为：2 * n + 1 ，其右孩子节点为2 * (n + 1)，其父节点为（n – 1） / 2 处
 *
 * ----------------------------------------------
 * 结构上相当于只完成了堆排序的第一步；但是这样的结构不是完整有序的，操作效率也不如 LinkedQueue 和 ArrayQueue；应用场景是什么？？？
 * 答：对外API：take()  保证出列的元素必然是最大的 (或最小的)
 *
 * </p>
 * <p>Date : 2019-01-15 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.containers.BlockingQueue.PriorityBlockingQueue;