package hj.action.DesignPatterns.Memento.example.game;

/**
 * <p>Title : 存档：备忘录</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-20 </p>
 *
 * @author : hejie
 */
public class Memento {

    private int blood;//血量
    private int blue;//蓝量

    public Memento(int blood, int blue) {
        this.blood = blood;
        this.blue = blue;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
