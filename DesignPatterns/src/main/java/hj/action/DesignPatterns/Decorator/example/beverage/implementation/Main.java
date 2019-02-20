package hj.action.DesignPatterns.Decorator.example.beverage.implementation;

import hj.action.DesignPatterns.Decorator.example.beverage.implementation.component.Beverage;
import hj.action.DesignPatterns.Decorator.example.beverage.implementation.component.DarkRoast;
import hj.action.DesignPatterns.Decorator.example.beverage.implementation.component.Espresso;
import hj.action.DesignPatterns.Decorator.example.beverage.implementation.decorator.Milk;
import hj.action.DesignPatterns.Decorator.example.beverage.implementation.decorator.Mocha;
import hj.action.DesignPatterns.Decorator.example.beverage.implementation.decorator.Soy;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class Main {

    public static void main(String[] args) {
        //不加料的饮品
        Beverage beverage = new DarkRoast();//Decaf, Espresso, HouseBlend

        //加料
        Beverage condimentBeverage = new Espresso();
        condimentBeverage = new Milk(condimentBeverage);//加牛奶
        condimentBeverage = new Mocha(condimentBeverage);//加摩卡
        condimentBeverage = new Soy(condimentBeverage);//加奶昔

        // 单独买饮料
        System.out.println(beverage.getDesc()+ ": $" + beverage.cost());

        // 买加调味品的饮料
        System.out.println(condimentBeverage.getDesc()+ ": $" + condimentBeverage.cost());

    }
}
