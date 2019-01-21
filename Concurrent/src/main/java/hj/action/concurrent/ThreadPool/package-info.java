/**
 * <p>Title : ThreadPool</p>
 * <p>Description :
 *
 * Executor：
 *  任务的执行者，线程池框架中几乎所有类都直接或者间接实现Executor接口，它是线程池框架的基础。
 *  Executor提供了一种将“任务提交”与“任务执行”分离开来的机制，它仅提供了一个execute()方法用来执行已经提交的Runnable任务。
 *
 * ExecutorService：
 *  继承Executor，它是“执行者服务”接口，它是为”执行者接口Executor”服务而存在的。
 *  ExecutorService提供了：
 *      ”将任务提交给执行者的接口(submit方法)”，
 *      ”让执行者执行任务(invokeAll, invokeAny方法)”的接口等等。
 *
 * AbstractExecutorService：
 *  抽象类，实现ExecutorService接口，为其提供默认实现。
 *  AbstractExecutorService除了实现ExecutorService接口外，还提供了newTaskFor()方法返回一个RunnableFuture，
 *  在运行的时候，它将调用底层可调用任务，作为 Future 任务，它将生成可调用的结果作为其结果，并为底层任务提供取消操作。
 *
 * ScheduledExecutorService：
 *  继承ExecutorService，为一个“延迟”和“定期执行”的ExecutorService。他提供了一些如下几个方法安排任务在给定的延时执行或者周期性执行。
 *
 * ThreadPoolExecutor：线程池
 *
 * ScheduledThreadPoolExecutor：
 *  ScheduledThreadPoolExecutor继承ThreadPoolExecutor并且实现ScheduledExecutorService接口，
 *  是两者的集大成者，相当于提供了“延迟”和“周期执行”功能的ThreadPoolExecutor。
 *
 * Executors：提供了Executor、ExecutorService、ScheduledExecutorService、ThreadFactory 、Callable等类的静态工厂方法。
 *      1、创建并返回设置有常用配置字符串的 ExecutorService 的方法。
 *      2、创建并返回设置有常用配置字符串的 ScheduledExecutorService 的方法。
 *      3、创建并返回“包装的”ExecutorService 方法，它通过使特定于实现的方法不可访问来禁用重新配置。
 *      4、创建并返回 ThreadFactory 的方法，它可将新创建的线程设置为已知的状态。
 *      5、创建并返回非闭包形式的 Callable 的方法，这样可将其用于需要 Callable 的执行方法中。
 *
 *------------------------------------------------------------------------------------------------------
 *
 * Future接口和实现Future接口的FutureTask代表了线程池的异步计算结果。
 *
 * Future：
 *  1、执行任务的取消
 *  2、查询任务是否完成
 *  3、获取任务的执行结果
 *
 * RunnableFuture：
 *  run()
 *
 * FutureTask
 *  实现RunnableFuture接口，既可以作为Runnable被执行，也可以作为Future得到Callable的返回值。
 *
 * </p>
 * <p>Date : 2019-01-18 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.ThreadPool;