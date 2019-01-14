/**
 * <p>Title : JVM垃圾收集器的各种实现</p>
 * <p>Description :
 *
 * 新生代收集器：Young generation
 *  1、Serial：最基础的收集器，复制算法，Client模式下的默认收集器
 *  2、ParNew：Serial的多线程版本，复制算法，并行，主要与老年代的CMS配合
 *  3、Parallel Scavenge：复制算法，并行，以提高吞吐量为目标，Server模式下的默认垃圾收集器
 *
 * 老年代收集器：Tenured generation
 *  1、Serial Old：标记-整理算法，Client模式下默认使用
 *  2、Parallel Old：Parallel Scavenge收集器的老年代版本，多线程并行，标记-整理算法，注重吞吐量，jdk1.6+
 *  3、CMS：并发收集，低停顿，不阻断用户进程，标记-清除算法，以获取最短停顿时间为目标
 *      缺点：消耗CPU；无法处理浮动垃圾；内存碎片
 *
 * 整堆收集：G1    JDK7 update 4
 *  将整堆划分为相等大小的区域（Region），并逻辑的区分新生代和老年代
 *  特性：
 *      1、并行与并发
 *      2、分代收集
 *      3、空间整合（结合多种收集算法，不产生碎片），Region内部：标记-整理算法；Region之间：复制算法，利于程序的长时间运行
 *      4、可预测的停顿，低停顿同时实现高吞吐量，可设定：M毫秒内，垃圾收集的时间不超过N毫秒
 *
 * </p>
 * <p>Date : 2019-01-07 </p>
 *
 * @author : hejie
 */
package hj.action.GarbageCollection.Collecters;