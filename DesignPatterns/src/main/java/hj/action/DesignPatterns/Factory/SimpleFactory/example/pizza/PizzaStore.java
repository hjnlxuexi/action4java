package hj.action.DesignPatterns.Factory.SimpleFactory.example.pizza;

/**
 * <p>Title : 披萨店</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class PizzaStore {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        //一个芝士披萨
        Pizza pizza = SimpleFactory4Pizza.createPizza("hj.action.DesignPatterns.Factory.SimpleFactory.example.pizza.CheesePizza");
        System.out.println();
        //一个素披萨
        Pizza pizza2 = SimpleFactory4Pizza.createPizza("hj.action.DesignPatterns.Factory.SimpleFactory.example.pizza.VeggiePizza");
    }
}
