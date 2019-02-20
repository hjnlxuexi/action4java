/**
 * <p>Title : 观察者模式案例：气象观测</p>
 * <p>Description :
 *
 * 在气象观测站中，它能够追踪目前的天气状况，包括温度、适度、气压。
 * 需要实现一个布告板，能够分别显示目前的状态，气象统计和简单的预报。
 * 当气象站中获取最新的测量数据时，三种布告板必须实时更新。
 *
 * 分析：
 *  天气数据--被观察者
 *  布告板--观察者
 *
 * </p>
 * <p>Date : 2019-02-12 </p>
 *
 * @author : hejie
 */
package hj.action.DesignPatterns.Observer.example.weather;