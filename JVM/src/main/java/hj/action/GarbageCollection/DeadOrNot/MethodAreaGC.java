package hj.action.GarbageCollection.DeadOrNot;

/**
 * <p>Title : 方法区回收</p>
 * <p>Description :
 *
 * 判断字面量常量是否被回收：当常量没有被任何引用，也没有引用字面量，则发生内存回收。
 *
 * 判断一个类回收的条件："无用的类"
 *  1、该类所有的实例对象都已经被回收，即java堆中不存在该类的任何实例；
 *  2、加载该类的ClassLoader已经被回收
 *  3、该类对应的java.lang.Class对象没有任何地方被引用，无法在任何地方通过反射访问该类的方法
 *
 *
 * -Xnoclassgc：控制是否对类进行回收；
 *
 * 查看类加载和卸载信息
 *  -verbose:class 加上
 *  -XX:+TraceClassLoading （Product版的虚拟机中使用）
 *  -XX:+TraceClassUnLoading （FastDebug版的虚拟机中使用）
 *
 *
 * 在大量使用反射、动态代理、CGLib等ByteCode框架、动态生成JSP以及OSGI这类频繁自定义ClassLoader的场景都需要JVM具备类卸载的功能，以保证方法区(永久代不会溢出)
 *
 * </p>
 * <p>Date : 2019-01-05 </p>
 *
 * @author : hejie
 */
public class MethodAreaGC {
}
