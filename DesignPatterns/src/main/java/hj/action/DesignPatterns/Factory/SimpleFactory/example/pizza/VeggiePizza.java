package hj.action.DesignPatterns.Factory.SimpleFactory.example.pizza;

/**
 * <p>Title : 素披萨</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class VeggiePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("prepare VeggiePizza ...");
    }

    @Override
    public void bake() {
        System.out.println("back VeggiePizza ...");
    }

    @Override
    public void cut() {
        System.out.println("cut VeggiePizza ...");
    }

    @Override
    public void box() {
        System.out.println("box VeggiePizza ...");
    }
}
