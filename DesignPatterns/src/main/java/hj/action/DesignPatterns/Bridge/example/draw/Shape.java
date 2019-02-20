package hj.action.DesignPatterns.Bridge.example.draw;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-19 </p>
 *
 * @author : hejie
 */
public abstract class Shape {

    Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw();
}
