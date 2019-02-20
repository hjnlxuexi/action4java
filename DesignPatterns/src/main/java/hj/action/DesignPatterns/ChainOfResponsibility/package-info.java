/**
 * <p>Title : 责任链模式</p>
 * <p>Description :
 *
 *  避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止，这就是职责链模式。
 *
 *  在职责链模式中最关键的一点就是客户提交请求后，请求沿着链往下传递直到有一个处理者处理它，在这里客户无需关心它的请求是哪个处理者来处理，反正总有一个处理者会处理它的请求。
 *  客户端和处理者都没有对方明确的信息，同时处理者也不知道职责链中的结构。所以职责链可以简化对象的相互连接，他们只需要保存一个指向其后续者的引用，而不需要保存所有候选者的引用。
 *
 *
 *  Handler: 抽象处理者。定义了一个处理请求的方法。所有的处理者都必须实现该抽象类。
 *  ConcreteHandler: 具体处理者。处理它所负责的请求，同时也可以访问它的后继者。如果它能够处理该请求则处理，否则将请求传递到它的后继者。
 *  Client: 客户类。
 *
 *  //------------------------------
 *
 *  public class ConcreteHandler extends Handler
 * {
 *    public void handleRequest(String request)
 *    {
 *        if(请求request满足条件)
 *        {
 *            ......  //处理请求；
 *        }
 *        else
 *        {
 *            this.successor.handleRequest(request); //转发请求
 *        }
 *    }
 * }
 *
 *  //------------------------------
 *
 *
 *  优点：
 *      1、降低耦合度。它将请求的发送者和接受者解耦。
 *      2、简化了对象。使得对象不需要知道链的结构。
 *      3、增强给对象指派职责的灵活性。通过改变链内的成员或者调动它们的次序，允许动态地新增或者删除责任。
 *      4、增加新的请求处理类很方便。
 *  缺点：
 *      1、不能保证请求一定被接收。
 *      2、系统性能将受到一定影响，而且在进行代码调试时不太方便；可能会造成循环调用。
 *      3、可能不容易观察运行时的特征，有碍于除错。
 *
 * </p>
 * <p>Date : 2019-02-19 </p>
 *
 * @author : hejie
 */
package hj.action.DesignPatterns.ChainOfResponsibility;