/**
 * <p>Title : 探究Java异常体系</p>
 * <p>Description :
 * Throwable：
 *      Error：
 *          错误，是程序无法处理的，如OutOfMemoryError、ThreadDeath等，出现这种情况你唯一能做的就是听之任之，交由JVM来处理，不过JVM在大多数情况下会选择终止线程。
 *
 *      Exception：
 *          CheckedException：检查性异常，编译时强制处理
 *          UncheckedException：非检查型异常，运行时抛出
 *
 * </p>
 * <p>Date : 2018/12/22 </p>
 *
 * @author : hejie
 */
package hj.action.basics.Throwable;