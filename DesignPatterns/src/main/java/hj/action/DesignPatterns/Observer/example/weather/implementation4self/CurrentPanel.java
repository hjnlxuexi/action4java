package hj.action.DesignPatterns.Observer.example.weather.implementation4self;


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
    /**
     *  TODO
     * 这里违反了：依赖倒置原则
     *
     * 但是，针对业务，被观察者Weather是明确的，所以这里不会有其他实现
     * 如果非要满足该原则，则需要把被观察者的业务数据拆解，变得复杂
     */
    private Weather weather;

    public CurrentPanel(Weather weather){
        // 注册 当前观察者
        weather.registerObserver(this);
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
     * 接收通知，执行自己的业务
     * 因为，已经持有了被观察者的实例，
     * 所以，不需要任何的赋值操作
     */
    @Override
    public void update() {
        //显示
        display();
    }
}
