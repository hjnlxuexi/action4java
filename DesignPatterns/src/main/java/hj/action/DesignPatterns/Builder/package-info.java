/**
 * <p>Title : 建造者模式</p>
 * <p>Description :
 *
 *  建造者模式将一个复杂对象的构建与表示分离，使得同样的构建过程可以创建不同的表示。
 *  用户只需关心最终的表现，而不需要关心具体的构建过程。
 *
 *
 *  结构：
 *      Builder：抽象建造者。它声明为创建一个Product对象的各个部件指定的抽象接口。
 *      ConcreteBuilder：具体建造者。实现抽象接口，构建和装配各个部件。
 *      Director：指挥者。构建一个使用Builder接口的对象。
 *          它主要是用于创建一个复杂的对象，它主要有两个作用，一是：隔离了客户与对象的生产过程，二是：负责控制产品对象的生产过程。
 *      Product：产品角色。一个具体的产品对象。
 *
 *
 *  优点：
 *      1、将复杂产品的创建步骤分解在不同的方法中，使得创建过程更加清晰，使得我们能够更加精确的控制复杂对象的产生过程。
 *      2、将产品的创建过程与产品本身分离开来，可以使用相同的创建过程来得到不同的产品。也就说细节依赖抽象。
 *      3、每一个具体建造者都相对独立，而与其他的具体建造者无关，
 *          因此可以很方便地替换具体建造者或增加新的具体建造者，用户使用不同的具体建造者即可得到不同的产品对象。
 *
 *  缺点：
 *      1、建造者模式所创建的产品一般具有较多的共同点，其组成部分相似，
 *          如果产品之间的差异性很大，则不适合使用建造者模式，因此其使用范围受到一定的限制。
 *      2、如果产品的内部变化复杂，可能会导致需要定义很多具体建造者类来实现这种变化，导致系统变得很庞大。
 *
 *
 *  适用场景：
 *      1、需要生成的产品对象有复杂的内部结构，这些产品对象通常包含多个成员属性。
 *      2、隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品。
 *
 * </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
package hj.action.DesignPatterns.Builder;