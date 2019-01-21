/**
 * <p>Title : ConcurrentSkipListMap</p>
 * <p>Description :
 *
 * 背景：
 *  Hash、TreeMap，这两种数据结构各自都有着优缺点：
 *      1、Hash表：插入、查找最快，为O(1)；如使用链表实现则可实现无锁；数据有序化需要显式的排序操作。
 *      2、红黑树：插入、查找为O(logn)，但常数项较小；无锁实现的复杂性很高，一般需要加锁；数据天然有序。
 *
 * Skip List ，称之为跳表，它是一种可以替代平衡树的数据结构，其数据元素默认按照key值升序，天然有序。
 * Skip list让已排序的数据分布在多层链表中，以0-1随机数决定一个数据的向上攀升与否。
 * 通过“空间来换取时间”的一个算法，在每个节点中增加了向前的指针，在插入、删除、查找时可以忽略一些不可能涉及到的结点，从而提高了效率。
 *
 * ------------------------------------------------------------------------------------------------------------------
 *
 * SkipList特征：
 *  1、由很多层结构组成，level是通过一定的概率随机产生的
 *  2、每一层都是一个有序的链表，默认是升序，也可以根据创建映射时所提供的Comparator进行排序，具体取决于使用的构造方法
 *  3、最底层(Level 1)的链表包含所有元素
 *  4、如果一个元素出现在Level i 的链表中，则它在Level i 之下的链表也都会出现
 *  5、每个节点包含两个指针，一个指向同一链表中的下一个元素，一个指向下面一层的元素
 *
 * ------------------------------------------------------------------------------------------------------------------
 *
 * 算法流程：
 *  1、从顶层找
 *  2、没找到，确定区间，到下一层找
 *  3、递归第二步，直到找到，或者到最后一层没有找到
 *
 * ------------------------------------------------------------------------------------------------------------------
 *
 * 插入：
 *  1、查找合适的位置。
 *      这里需要明确一点就是在确认新节点要占据的层次K时，采用丢硬币的方式，完全随机。
 *      如果占据的层次K大于链表的层次，则重新创建新的层，否则插入指定层次，需要确保所有小于K层的层次都应该出现新节点。
 *  2、申请新的节点
 *  3、调整指针
 *
 *
 * ------------------------------------------------------------------------------------------------------------------
 * ConcurrentSkipListMap 结构：
 *  1、Node 实现底层链表
 *  2、Index 表示为基于Node的索引层
 *  3、HeadIndex 用来维护索引层次
 *
 * </p>
 * <p>Date : 2019-01-15 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.containers.ConcurrentSkipListMap;