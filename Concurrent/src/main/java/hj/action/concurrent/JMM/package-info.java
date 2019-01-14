/**
 * <p>Title : 探究Java内存模型(JMM)</p>
 * <p>Description :
 *
 * 从JDK 5 开始，JMM就使用happens-before的概念来阐述多线程之间的内存可见性
 *
 * JMM的重排序，as-if-serial语义，是保证单线程下，且不影响最终结果的前提下的优化重排序
 *
 * volatile：
 *  通过即时刷新主内存达到线程间可见，满足happens-before的可见性规则；
 *  volatile禁止重排序。
 *
 *  1、如果第一个操作为volatile读，则不管第二个操作是啥，都不能重排序。这个操作确保volatile读之后的操作不会被编译器重排序到volatile读之前；
 *  2、当第二个操作为volatile写是，则不管第一个操作是啥，都不能重排序。这个操作确保volatile写之前的操作不会被编译器重排序到volatile写之后；
 *  3、当第一个操作volatile写，第二操作为volatile读时，不能重排序。
 *
 *  内存屏障策略：
 *      StoreStore屏障可以保证在volatile写之前，其前面的所有普通写操作都已经刷新到主内存中。
 *      StoreLoad屏障的作用是避免volatile写与后面可能有的volatile读/写操作重排序。
 *      LoadLoad屏障用来禁止处理器把上面的volatile读与下面的普通读重排序。
 *      LoadStore屏障用来禁止处理器把上面的volatile读与下面的普通写重排序。
 *
 * </p>
 * <p>Date : 2018/12/20 </p>
 *
 * @author : hejie
 */
package hj.action.concurrent.JMM;