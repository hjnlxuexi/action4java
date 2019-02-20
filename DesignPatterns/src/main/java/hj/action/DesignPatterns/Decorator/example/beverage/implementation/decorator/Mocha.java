package hj.action.DesignPatterns.Decorator.example.beverage.implementation.decorator;

import hj.action.DesignPatterns.Decorator.example.beverage.implementation.component.Beverage;

/**
 * <p>Title : 摩卡</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class Mocha extends CondimentDecorator {
    /**
     * 持有饮料引用
     */
    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    /**
     * 加摩卡的费用
     * @return cost
     */
    @Override
    public double cost() {
        return beverage.cost() + 0.50;
    }

    /**
     * 增加额外装饰的能力
     *
     * @return desc
     */
    @Override
    public String getDesc() {
        return beverage.getDesc() + " + Mocha";
    }
}
