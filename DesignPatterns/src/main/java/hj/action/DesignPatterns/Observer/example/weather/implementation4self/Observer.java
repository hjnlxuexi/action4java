package hj.action.DesignPatterns.Observer.example.weather.implementation4self;

/**
 * <p>Title : 观察者接口</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-12 </p>
 *
 * @author : hejie
 */
public interface Observer {

    /**
     * 接收通知，执行自己的业务
     */
    void update();
}
