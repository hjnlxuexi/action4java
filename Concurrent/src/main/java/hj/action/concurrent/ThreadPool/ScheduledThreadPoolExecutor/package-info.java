/**
 * <p>Title : ScheduledThreadPoolExecutor：定时、周期调度线程池</p>
 * <p>Description :
 * Timer与TimerTask虽然可以实现线程的周期和延迟调度，但是Timer与TimerTask存在一些缺陷，官方建议采用ScheduledThreadPoolExecutor
 *
 * 四个调度器：
 *  1、schedule(Callable callable, long delay, TimeUnit unit)
 *      创建并执行在给定延迟后启用的 ScheduledFuture。
 *  2、schedule(Runnable command, long delay, TimeUnit unit)
 *      创建并执行在给定延迟后启用的一次性操作。
 *  3、scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
 *      创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；也就是将在 initialDelay 后开始执行，然后在 initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推。
 *  4、scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
 *      创建并执行一个在给定初始延迟后首次启用的定期操作，随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟。
 *
 * </p>
 * <p>Date : 2019-01-20 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.ThreadPool.ScheduledThreadPoolExecutor;