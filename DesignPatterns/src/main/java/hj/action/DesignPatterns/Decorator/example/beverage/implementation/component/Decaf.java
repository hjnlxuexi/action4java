package hj.action.DesignPatterns.Decorator.example.beverage.implementation.component;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class Decaf extends Beverage {

    public Decaf() {
        this.desc = "Decaf";
    }

    @Override
    public double cost() {
        return 2.08;
    }
}
