package hj.action.DesignPatterns.Observer.example.weather.implementation4java;

import java.util.*;

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
public class Weather extends Observable {

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
        setChanged();//java自带的的这里有点拖沓，一会儿重新实现
        notifyObservers();
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
        //通知所有观察者
        setChanged();
        notifyObservers();
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
        //通知所有观察者
        setChanged();
        notifyObservers();
    }


}
