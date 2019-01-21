/**
 * <p>Title : ThreadPoolExecutor</p>
 * <p>Description :
 *
 * 线程的5中状态：新建、就绪、运行、阻塞、死亡
 *
 * 线程池5种状态：Running、Shutdown、Stop、Tidying、Terminated
 *  RUNNING:  Accept new tasks and process queued tasks
 *            - 接收新任务 并 处理队列中的任务
 *  SHUTDOWN: Don't accept new tasks, but process queued tasks
 *            - 不接收新任务，但是会继续处理队列中的任务
 *  STOP:     Don't accept new tasks, don't process queued tasks, and interrupt in-progress tasks
 *            - 停止接收、停止处理、中断当前处理的任务
 *  TIDYING:  All tasks have terminated, workerCount is zero, the thread transitioning to state TIDYING will run the terminated() hook method
 *            - 所有的任务都终止了，workerCount=0，转换到状态TIDYING的线程将运行terminate()钩子方法
 *  TERMINATED: terminated() has completed
 *
 *  状态转换顺序：
 *      1、RUNNING -> SHUTDOWN
 *          在调用shutdown()时，可能隐含在finalize()中；[ ThreadPoolExecutor重写了finalize()方法，直接调用shutdown()，GC回收线程对象时保障线程池的状态 ]
 *      2、(RUNNING or SHUTDOWN) -> STOP
 *          调用shutdownNow()
 *      3、SHUTDOWN -> TIDYING
 *          任务队列为空，并且 线程池为空
 *      4、STOP -> TIDYING
 *          线程池为空
 *      5、TIDYING -> TERMINATED
 *          调用terminated()
 *
 *  private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
 *  用一个原子整数类型来记录控制量，高3位：记录线程池状态，低29位：记录线程池中线程数量
 *
 * -------------------------------------------------------------------------------------------------
 * public ThreadPoolExecutor(int corePoolSize,
 *                               int maximumPoolSize,
 *                               long keepAliveTime,
 *                               TimeUnit unit,
 *                               BlockingQueue<Runnable> workQueue,
 *                               ThreadFactory threadFactory,
 *                               RejectedExecutionHandler handler)
 *      corePoolSize：线程池中核心线程的数量。核心线程会一直存活。
 *          当提交一个任务时，线程池会新建一个线程来执行任务，直到当前线程数等于corePoolSize。
 *          如果调用了线程池的prestartAllCoreThreads()方法，线程池会提前创建并启动所有基本线程。
 *      maximumPoolSize：线程池中允许的最大线程数。
 *      keepAliveTime：线程空闲的时间。只针对 maximumPoolSize - corePoolSize 这部分线程的回收。
 *      unit：keepAliveTime的时间单位，
 *      workQueue：用来保存等待执行的任务的阻塞队列，等待的任务必须实现Runnable接口。如：
 *          ArrayBlockingQueue：基于数组结构的有界阻塞队列，FIFO
 *          LinkedBlockingQueue：基于链表结构的有界阻塞队列，FIFO。
 *          SynchronousQueue：不存储元素的阻塞队列，每个插入操作都必须等待一个移出操作，反之亦然。
 *          PriorityBlockingQueue：具有优先界别的阻塞队列。
 *      threadFactory：用于设置创建线程的工厂。
 *      handler：线程池的拒绝策略。
 *          拒绝策略，是指将任务添加到线程池中时，线程池拒绝该任务所采取的相应策略。
 *          新任务来时，任务队列满了，进程数也饱和了，则通过拒绝策略处理。
 *          1、AbortPolicy：直接抛出异常，默认策略；
 *          2、CallerRunsPolicy：用调用者所在的线程来执行任务；
 *          3、DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
 *          4、DiscardPolicy：直接丢弃任务；
 * 注：
 *  当线程池中的线程数量等于corePoolSize 时，如果继续提交任务，该任务会被添加到阻塞队列workQueue中，当阻塞队列也满了之后，则线程池会新建线程执行任务直到maximumPoolSize
 *
 *
 * -------------------------------------------------------------------------------------------------
 * Executors提供的线程池种类：
 *  FixedThreadPool：固定线程数量的线程池。corePoolSiz = maximumPoolSize
 *      workQueue：LinkedBlockingQueue 任务队列大小 Integer.MAX_VALUE  "无界队列"
 *  SingleThreadExecutor：只有一个工作线程的FixedThreadPool。corePoolSiz = maximumPoolSize = 1
 *  CachedThreadPool：根据需要创建新线程的线程池。 corePoolSiz = 0，maximumPoolSize = Integer.MAX_VALUE
 *      workQueue：SynchronousQueue
 *      keepAliveTime：60秒
 *      这样就会存在一个问题，如果主线程提交任务的速度远远大于CachedThreadPool的处理速度，则CachedThreadPool会不断地创建新线程来执行任务，这样有可能会导致系统耗尽CPU和内存资源
 *
 * </p>
 * <p>Date : 2019-01-18 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.ThreadPool.ThreadPoolExecutor;