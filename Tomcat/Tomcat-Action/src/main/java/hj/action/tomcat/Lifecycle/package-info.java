/**
 * <p>Title : 生命周期</p>
 * <p>Description :
 *
 * Lifecycle接口是一个公用的接口，定义了组件生命周期的一些方法，用于启动、停止Catalina组件。
 * 组件的生命周期包括：init、start、stop、destroy。
 * 以及各种事件的常量、操作LifecycleListener的API，典型的观察者模式。
 *
 * -------------------------------------------------------------------------------------------------------
 * LifecycleBase：抽象类，实现LifeCycle接口的核心逻辑，
 *      CopyOnWriteArrayList：存放LifecycleListener，各个生命周期，其中的LifecycleListener是在变化的，所以考虑并发问题，使用并发List。
 *          StandardServer、StandardService、StandardEngine、StandardHost都是继承LifecycleBase公共父类，所以各自拥有自己的lifecycleListeners。
 *      state：初始值是LifecycleState.NEW，也存在并发修改的问题，用了volatile修饰。
 *      fireLifecycleEvent：触发事件，子类可以在适当的时机调用该方法发出事件通知。事件通知由LifecycleListener实现类决定要不要对特定的事件进行处理。
 *      setState：更新生命周期状态，并调用fireLifecycleEvent发出对应的事件通知。
 *
 * MBeanRegistration：JmxEnabled的父接口，提供JMX支持，管理MBean
 *
 * LifecycleMBeanBase：继承于LifecycleBase，实现JmxEnabled，既有生命周期特性又提供JMX管理特性
 *  为了保证jmx的正常注册和注销，要求子类在重写initInternal、destroyInternal方法时，必须先调用super.initInternal()。
 *
 * LifecycleState：枚举，生命周期的状态与事件之间的映射关系
 *
 * LifecycleListener：生命周期的监听接口，通过事件触发。在server.xml中注册，也就是调用Lifecycle.addLifecycleListener()接口注册
 *
 * </p>
 * <p>Date : 2019-01-26 </p>
 *
 * @author : hejie
 */
package hj.action.tomcat.Lifecycle;