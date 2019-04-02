package hj.action.classloader;

import hj.action.classloader.clazzes.Son;

/**
 * <p>Title : 判断打印顺序</p>
 * <p>Description :
 *
 * <b>目的：</b>考察java类加载的机制
 *
 * <b>预期：</b>爷爷在静态代码块==》爸爸在静态代码块==》儿子在静态代码块==》25
 * <b>实际：</b>爷爷在静态代码块==》爸爸在静态代码块==》25
 *
 * <b>Java类加载的七个阶段：<b/>
 *  加载、验证、准备、解析、初始化、使用、卸载
 *
 *  <b>加载：</b>
 *  将代码文件(.class)加载到内存，对应在JVM的方法区创建Class对象。
 *
 *  <b>验证：</b>
 *  JVM对字节码流进行校验：JVM规范校验、代码逻辑校验
 *  JVM规范校验：
 *      格式校验、版本校验等
 *  代码逻辑校验：
 *      确保 JVM 运行该字节码文件后不会出现致命错误
 *
 *  <b>准备：</b>
 *  JVM 为类变量分配内存并初始化
 *  内存分配的对象：类变量（ static修饰的变量 ）
 *  初始化的类型：JVM 为类变量赋予 Java 语言中该数据类型的<b>零值</b>，而不是用户代码里初始化的值。
 *  但，如果是 static final 修饰的类变量，在准备阶段，会直接赋予目标值
 *
 *  <b>解析：</b>
 *  当通过准备阶段之后，JVM 针对类或接口、字段、类方法、接口方法、方法类型、方法句柄和调用点限定符 7 类引用进行解析。
 *  这个阶段的主要任务是将其在常量池中的符号引用替换成直接其在内存中的直接引用。
 *
 *  <b>初始化：</b>
 *  https://mp.weixin.qq.com/s/16MKwhwPHVNezxS5ygk-cA
 *
 *  <b>使用：</b>
 *  通过入口方法调用。
 *
 *  <b>卸载：</b>
 *  当用户程序代码执行完毕后，JVM 便开始销毁创建的 Class 对象，最后负责运行的 JVM 也退出内存。
 *
 * <b>分析：</b>
 * 为什么没有打印：儿子在静态代码块？
 * 这是因为对于静态字段，只有直接定义这个字段的类才会被初始化（执行静态代码块），
 * 因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
 *
 *
 * <b>分析方法论</b>
 * 确定类变量的初始值。在类加载的准备阶段，JVM 会为类变量初始化零值，这时候类变量会有一个初始的零值。如果是被 final 修饰的类变量，则直接会被初始成用户想要的值。
 * 初始化入口方法。当进入类加载的初始化阶段后，JVM 会寻找整个 main 方法入口，从而初始化 main 方法所在的整个类。当需要对一个类进行初始化时，会首先初始化类构造器，之后初始化对象构造器。
 * 初始化类构造器。初始化类构造器是初始化类的第一步，其会按顺序收集类变量的赋值语句、静态代码块，最终组成类构造器由 JVM 执行。
 * 初始化对象构造器。初始化对象构造器是在类构造器执行完成之后的第二部操作，其会按照执行类成员变成赋值、普通代码块的顺序收集代码，最终组成对象构造器，最终由 JVM 执行。
 *
 *
 * </p>
 * <p>Date : 2018/12/6 </p>
 *
 * @author : hejie
 */
public class Main {
    Main(){
        System.out.println("实例化！");
    }
    public static void main(String[] args)
    {
        System.out.println("爸爸的岁数:" + Son.factor);    //入口
        System.out.println("爷爷的岁数:" + Son.age);    //入口
    }
}