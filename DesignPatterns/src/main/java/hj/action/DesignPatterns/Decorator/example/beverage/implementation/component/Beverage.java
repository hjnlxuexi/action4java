package hj.action.DesignPatterns.Decorator.example.beverage.implementation.component;

/**
 * <p>Title : 饮料</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-13 </p>
 *
 * @author : hejie
 */
public abstract class Beverage {

    protected String desc = "Unknown Beverage";

    public abstract double cost();


    /**
     * 对外暴露，最少的接口
     *
     * 不应该想当然的，添加set方法，因为对于这个类来说是不需要的
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

}
