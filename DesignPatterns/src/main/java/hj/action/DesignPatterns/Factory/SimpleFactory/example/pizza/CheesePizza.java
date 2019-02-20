package hj.action.DesignPatterns.Factory.SimpleFactory.example.pizza;

/**
 * <p>Title : 芝士披萨</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class CheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("prepare CheesePizza ...");
    }

    @Override
    public void bake() {
        System.out.println("back CheesePizza ...");
    }

    @Override
    public void cut() {
        System.out.println("cut CheesePizza ...");
    }

    @Override
    public void box() {
        System.out.println("box CheesePizza ...");
    }
}
