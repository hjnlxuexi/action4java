package hj.action.DesignPatterns.Factory.SimpleFactory.example.pizza;

/**
 * <p>Title : 披萨</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public interface Pizza {

    void prepare();

    void bake();

    void cut();

    void box();
}
