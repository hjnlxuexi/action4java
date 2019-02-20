package hj.action.DesignPatterns.Decorator.example.beverage.implementation.component;

/**
 * <p>Title : 咖啡：HouseBlend</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        this.desc = "HouseBlend";
    }

    @Override
    public double cost() {
        return 0.88;
    }
}
