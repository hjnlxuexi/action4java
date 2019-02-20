package hj.action.DesignPatterns.Bridge.example.draw;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-19 </p>
 *
 * @author : hejie
 */
public class Rectangle extends Shape {
    @Override
    public void draw() {
        color.bepaint("矩形");
    }
}
