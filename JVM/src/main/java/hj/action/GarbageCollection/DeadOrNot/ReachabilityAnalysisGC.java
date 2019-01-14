package hj.action.GarbageCollection.DeadOrNot;

/**
 * <p>Title : 可达性分析算法的GC</p>
 * <p>Description :
 *
 * 原理：构建一个树形的引用链
 *      根节点：GC Roots
 *          |-> Reference Chain 对象引用链
 *
 * GC Roots：
 *  1、虚拟机栈引用的对象，即局部变量引用的对象
 *  2、方法区中静态属性引用的对象
 *  3、方法区中常量引用的对象
 *  4、本地方法栈中JNI引用的对象
 *
 *
 * </p>
 * <p>Date : 2018/12/31 </p>
 *
 * @author : hejie
 */
public class ReachabilityAnalysisGC {
}
