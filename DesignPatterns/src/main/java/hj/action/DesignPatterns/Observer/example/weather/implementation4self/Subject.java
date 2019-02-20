package hj.action.DesignPatterns.Observer.example.weather.implementation4self;

/**
 * <p>Title : 主题：被观察者接口</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-12 </p>
 *
 * @author : hejie
 */
public interface Subject {

    /**
     * 注册观察者
     * @param observer 观察者
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     * @param observer 观察者
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有观察者
     */
    void notifyObservers();
}
