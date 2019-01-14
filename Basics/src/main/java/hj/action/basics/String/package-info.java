/**
 * <p>Title : 与字符串相关的类</p>
 * <p>Description :
 * String：
 *      final，不能被继承；
 *      对象不可变，每一次修改，都是new新的对象；
 *      利用"字符串常量池"，命中相同的字符串常量时，直接引用，提高效率，节省内存空间；
 *      推荐：s = "s"，而不是：s = new String("s")
 *
 * StringBuffer:
 *      单个对象，修改时不创建新对象，比String节省内存空间；
 *      线程安全。
 *
 * StringBuilder:
 *      类似StringBuffer，但是线程非安全；
 *      执行效率高。
 *
 *
 *
 * </p>
 * <p>Date : 2018/12/21 </p>
 *
 * @author : hejie
 */
package hj.action.basics.String;