/**
 * <p>Title : 摸一摸内部类</p>
 * <p>Description :
 *
 * 为什么要使用内部类？
 *   《Think in Java》使用内部类最吸引人的原因是：每个内部类都能独立地继承一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有影响。
 *
 *
 *   也就是说，内部类可以穿透外部类的限制，物理上是内部的，是在能力上可以自由扩展。为程序的设计带来更多可能性。
 *   另一方面，实现了类之间的多重继承。
 *
 *
 *   内部类是编译时概念，编译完成后，就是两个.class文件，两个完全不同的类。但二者还是有内在联系
 *
 *
 *
 * </p>
 * <p>Date : 2018/12/21 </p>
 *
 * @author : hejie
 */
package hj.action.basics.InnerClass;