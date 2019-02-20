package hj.action.DesignPatterns.Decorator.example.beverage.implementation.decorator;

import hj.action.DesignPatterns.Decorator.example.beverage.implementation.component.Beverage;

/**
 * <p>Title : 奶昔</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class Soy extends CondimentDecorator {
    /**
     * 持有饮料的引用
     */
    private Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }

    /**
     * 增加额外装饰的能力
     *
     * @return desc
     */
    @Override
    public String getDesc() {
        return beverage.getDesc() + " + Soy";
    }
}
