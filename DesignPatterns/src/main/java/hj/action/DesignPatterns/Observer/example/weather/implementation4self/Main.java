package hj.action.DesignPatterns.Observer.example.weather.implementation4self;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-12 </p>
 *
 * @author : hejie
 */
public class Main {

    public static void main(String[] args) {
        Weather weather = new Weather();
        CurrentPanel currentPanel = new CurrentPanel(weather);

        weather.setTemperature(30);
        weather.setHumidity(15);
        weather.setPressure(101);
    }
}
