package hj.action.DesignPatterns.Bridge.example.draw;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-19 </p>
 *
 * @author : hejie
 */
public class Green implements Color {
    /**
     * 上色
     *
     * @param shape 为哪个形状上色
     */
    @Override
    public void bepaint(String shape) {
        System.out.println("绿色的"+shape);
    }
}
