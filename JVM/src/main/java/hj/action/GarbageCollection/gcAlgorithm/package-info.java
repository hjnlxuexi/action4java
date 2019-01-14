/**
 * <p>Title : 垃圾收集算法</p>
 * <p>Description :
 *
 * 1、标记-清除算法
 *      效率低； 内存空间碎片
 *
 * 2、复制算法
 *      空间浪费50%
 *      优化：Eden + Survivor*2   Eden:Survivor = 8:1
 *          每次GC发生时，Eden + 一个Survivor的 存活对象 搬移到另一个 Survivor中；
 *          当Survivor空间不够时，通过额外空间来担保（老年代）
 *
 * 3、标记-整理算法
 *      将存活的对象有序的整理到内存的一端
 *
 * 4、分代收集算法
 *      新生代：复制算法
 *      老年代：标记-整理算法
 *
 * </p>
 * <p>Date : 2019-01-05 </p>
 *
 * @author : hejie
 */
package hj.action.GarbageCollection.gcAlgorithm;