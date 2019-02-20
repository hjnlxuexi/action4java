package hj.action.DesignPatterns.Decorator.example.beverage.implementation.component;

/**
 * <p>Title : 咖啡：DarkRoast</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        this.desc = "DarkRoast";
    }

    @Override
    public double cost() {
        return 1.08;
    }
}
