/**
 * <p>Title : 装饰者模式</p>
 * <p>Description :
 *
 * 给一个类或者对象添加能力：
 *      1、继承：静态的，不便于控制增加行为的方式和时机。
 *      2、关联：将其他对象作为自己的成员，由自己决定是否使用其他对象的各种能力。封装性，松耦合，创建更多对象
 *
 * 装饰者模式：
 *      采用关联组合的方式，动态地将责任附加到对象上。若要扩展功能，装饰者提供了比继承更加有弹性的替代方案。
 *      装饰者和被装饰者对象有相同的超类型。
 *
 * ===============================================================================================================
 * Component: 抽象构件。
 *      是定义一个对象接口，可以给这些对象动态地添加职责。
 *
 * ConcreteComponent: 具体构件。
 *      是定义了一个具体的对象，也可以给这个对象添加一些职责。
 *
 * CondimentDecorator: 抽象装饰类。
 *      是装饰抽象类，继承了Component,从外类来扩展Component类的功能，但对于Component来说，是无需知道Decorator存在的。
 *
 * ConcreteDecorator: 具体装饰类。
 *      起到给Component添加职责的功能。
 *
 * ===============================================================================================================
 * 优点：
 *      1、装饰者模式可以提供比继承更多的灵活性
 *      2、可以通过一种动态的方式来扩展一个对象的功能，在运行时选择不同的装饰器，从而实现不同的行为
 *      3、通过使用不同的具体装饰类以及这些装饰类的排列组合，可以创造出很多不同行为的组合。可以使用多个具体装饰类来装饰同一对象，得到功能更为强大的对象
 *      4、具体构件类与具体装饰类可以独立变化，用户可以根据需要增加新的具体构件类和具体装饰类，在使用时再对其进行组合，原有代码无须改变，符合“开闭原则”
 * 缺点：
 *      1、产生很多小对象，增加系统复杂性
 *      2、这种比继承更加灵活机动的特性，也同时意味着装饰模式比继承更加易于出错，排错也很困难，对于多次装饰的对象，调试时寻找错误可能需要逐级排查，较为烦琐。
 *
 * ===============================================================================================================
 * 适用场景：
 *      1、在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责。
 *      2、需要动态地给一个对象增加功能，这些功能也可以动态地被撤销。当不能采用继承的方式对系统进行扩充或者采用继承不利于系统扩展和维护时。
 *
 * </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
package hj.action.DesignPatterns.Decorator;