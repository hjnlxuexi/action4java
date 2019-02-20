package hj.action.DesignPatterns.Decorator.example.beverage.implementation.decorator;

import hj.action.DesignPatterns.Decorator.example.beverage.implementation.component.Beverage;

/**
 * <p>Title : 牛奶</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class Milk extends CondimentDecorator {

    /**
     * 持有被装饰组件的引用
     */
    private Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    /**
     * 加牛奶的价格
     * @return cost
     */
    @Override
    public double cost() {
        return beverage.cost() + 0.20;//饮料+牛奶 的价格
    }

    /**
     * 增加额外装饰的能力
     *
     * @return desc
     */
    @Override
    public String getDesc() {
        return beverage.getDesc() + " + Milk";
    }
}
