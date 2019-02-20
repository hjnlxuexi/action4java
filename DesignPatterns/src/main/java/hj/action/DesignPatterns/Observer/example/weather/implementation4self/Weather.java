package hj.action.DesignPatterns.Observer.example.weather.implementation4self;


import java.util.HashSet;
import java.util.Set;

/**
 * <p>Title : 天气数据：被观察者</p>
 * <p>Description :
 *
 * 利用java的 被观察者 Observable 作为父类
 *
 * </p>
 * <p>Date : 2019-02-12 </p>
 *
 * @author : hejie
 */
public class Weather implements Subject {

    private Set<Observer> observers = new HashSet<>();

    /**
     * 温度
     */
    private float temperature;

    /**
     * 湿度
     */
    private float humidity;

    /**
     * 气压
     */
    private float pressure;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        //通知所有观察者
        notifyObservers();
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
        //通知所有观察者
        notifyObservers();
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
        //通知所有观察者
        notifyObservers();
    }


    /**
     * 注册观察者
     *
     * @param observer 观察者
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * 移除观察者
     *
     * @param observer 观察者
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 通知所有观察者
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
