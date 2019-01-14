/**
 * <p>Title : JMM重排序</p>
 * <p>Description :
 *
 * 在执行程序时，为了提供性能，处理器和编译器常常会对指令进行重排序，但是不能随意重排序。
 * 满足条件：
 *  1、在单线程环境下不能改变程序运行的结果；
 *  1、存在数据依赖关系的不允许重排序
 *
 * as-if-serial语义：只保证单线程环境，多线程环境下无效。
 *
 *
 * </p>
 * <p>Date : 2018/12/26 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JMM.reOrder;