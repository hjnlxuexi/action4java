package hj.action.DesignPatterns.Decorator.example.beverage.implementation.component;

/**
 * <p>Title : 咖啡：Espresso</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class Espresso extends Beverage {

    public Espresso() {
        this.desc = "Espresso";
    }

    @Override
    public double cost() {
        return 1.12;
    }
}
