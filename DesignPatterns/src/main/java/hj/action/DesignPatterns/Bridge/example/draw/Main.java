package hj.action.DesignPatterns.Bridge.example.draw;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-19 </p>
 *
 * @author : hejie
 */
public class Main {

    public static void main(String[] args) {
        Shape circle = new Circle();
        Color blue = new Blue();
        circle.setColor(blue);

        circle.draw();


        Color green = new Green();
        circle.setColor(green);
        circle.draw();

    }
}
