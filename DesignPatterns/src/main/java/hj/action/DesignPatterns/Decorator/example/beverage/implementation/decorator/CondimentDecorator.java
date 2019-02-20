package hj.action.DesignPatterns.Decorator.example.beverage.implementation.decorator;

import hj.action.DesignPatterns.Decorator.example.beverage.implementation.component.Beverage;

/**
 * <p>Title : 装饰者：调味品</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public abstract class CondimentDecorator extends Beverage {

    /**
     * 增加额外装饰的能力
     * @return desc
     */
    public abstract String getDesc();
}
