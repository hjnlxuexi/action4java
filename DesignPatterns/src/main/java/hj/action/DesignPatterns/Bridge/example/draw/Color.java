package hj.action.DesignPatterns.Bridge.example.draw;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-19 </p>
 *
 * @author : hejie
 */
public interface Color {

    /**
     * 上色
     * @param shape 为哪个形状上色
     */
    void bepaint(String shape);
}
