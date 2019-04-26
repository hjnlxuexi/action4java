/**
 * <p>Title : CompletableFuture</p>
 * <p>Description :
 *  Doug大爷 鬼斧神工，颠覆了对异步编程的认知
 *
 *  1、定义：CompletableFuture<T> implements Future<T>, CompletionStage<T>
 *  2、入口：
 *      runAsync(Runnable runnable)：无返回值
 *      supplyAsync(Supplier<U> supplier)：有返回值
 *  3、内部执行线程池
 *      默认：公共的 ForkJoinPool
 *      自定义线程池：
 *      runAsync(Runnable runnable, Executor executor)
 *      supplyAsync(Supplier<U> supplier,Executor executor)
 *  4、工作流完成时序关系：CompletionStage接口
 *      4.1、串行关系：
 *          thenApply(Function<? super T,? extends U> fn)：有参数，有返回
 *          thenAccept(Consumer<? super T> action)：有参数，无返回
 *          thenRun(Runnable action)：无参数，无返回
 *          thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)：带有子流程的 thenApply
 *          以上为同步执行，xxxAsync表示异步执行
 *      4.2、AND汇聚关系
 *          thenCombine(other, fn);
 *          thenCombineAsync(other, fn);
 *          thenAcceptBoth(other, consumer);
 *          thenAcceptBothAsync(other, consumer);
 *          CompletionStage<Void> runAfterBoth(other, action);
 *          runAfterBothAsync(other, action);
 *      4.2、OR汇聚关系
 *          applyToEither(other, fn);
 *          applyToEitherAsync(other, fn);
 *          acceptEither(other, consumer);
 *          acceptEitherAsync(other, consumer);
 *          runAfterEither(other, action);
 *          runAfterEitherAsync(other, action);
 *  5、异步编程 异常处理
 *          exceptionally(Function<Throwable, ? extends T> fn)：类似于 catch{}
 *          whenComplete(BiConsumer<? super T, ? super Throwable> action)：无返回值的 finally{}
 *          handle(BiFunction<? super T, Throwable, ? extends U> fn)：有返回值的 finally{}
 *
 *  6、使用
 *      CompletableFuture<String> c1 = CompletableFuture
 *              .supplyAsync(()->"可以除0吗？"))
 *              .thenApply(r->r);
 *      CompletableFuture<Integer> c2 = CompletableFuture
 *              .supplyAsync(()->7/0))
 *              .thenApply(r->r*10)
 *              .exceptionally(e->0);
 *
 *      CompletableFuture<String> c3 = c1.thenCombine(c2,(s,i)->{
 *          return s + i
 *      });
 *
 *      System.out.println(c3.join());
 *
 * </p>
 * <p>Date : 2019-04-24 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.CompletableFuture;