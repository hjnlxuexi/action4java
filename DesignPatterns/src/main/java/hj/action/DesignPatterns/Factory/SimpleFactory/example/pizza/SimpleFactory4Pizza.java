package hj.action.DesignPatterns.Factory.SimpleFactory.example.pizza;

/**
 * <p>Title : 工厂</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public class SimpleFactory4Pizza {

    /**
     * 静态工厂方法
     * @param type 类型，
     *             这里采用直接全类名，也可用通过字典映射为其他间接字符串
     * @return 实例
     * @throws ClassNotFoundException e
     * @throws IllegalAccessException e
     * @throws InstantiationException e
     */
    public static Pizza createPizza(String type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //Pizza pizza = (Pizza) Class.forName(type).newInstance();
        Pizza pizza = (Pizza) (ClassLoader.getSystemClassLoader().loadClass(type)).newInstance();
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
