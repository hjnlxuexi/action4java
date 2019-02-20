package hj.action.DesignPatterns.Observer.example.weather.implementation4java;

import java.util.Observable;
import java.util.Observer;

/**
 * <p>Title : 显示当前的布告板</p>
 * <p>Description :
 *
 * 观察者
 * 显示板
 *
 * </p>
 * <p>Date : 2019-02-12 </p>
 *
 * @author : hejie
 */
public class CurrentPanel implements Observer,Display {
    private Weather weather;

    public CurrentPanel(Weather weather){
        // 注册 当前观察者
        weather.addObserver(this);
        this.weather = weather;
    }
    /**
     * 显示天气信息
     */
    @Override
    public void display() {
        System.out.println("===============当前天气=====================");
        System.out.println("温度："+this.weather.getTemperature());
        System.out.println("湿度："+this.weather.getHumidity());
        System.out.println("气压："+this.weather.getPressure());
        System.out.println();
    }

    /**
     * 接收通知，执行业务操作
     *
     * @param o   被观察者
     * @param arg 额外参数
     */
    @Override
    public void update(Observable o, Object arg) {
        //这里的weather和 this.weather 是同一个对象
        //所以，用java自带的观察者感觉有点多余，一会儿重写一下
        Weather weather = (Weather)o;
        System.out.println("同一份天气数据："+weather.equals(this.weather));

        //显示
        display();
    }
}
