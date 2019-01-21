/**
 * <p>Title : Exchanger</p>
 * <p>Description :
 *
 * Exchanger，它允许在并发任务之间交换数据。
 * 具体来说，Exchanger类允许在两个线程之间定义同步点。
 * 当两个线程都到达同步点时，他们交换数据结构，因此第一个线程的数据结构进入到第二个线程中，第二个线程的数据结构进入到第一个线程中。
 *
 * </p>
 * <p>Date : 2019-01-14 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JUC.tools.Exchanger;