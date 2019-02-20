package hj.action.DesignPatterns.Memento.example.game;

/**
 * <p>Title : 游戏角色</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-20 </p>
 *
 * @author : hejie
 */
public class Role {
    private int blood;//血量
    private int blue;//蓝量

    public Role(int blood, int blue) {
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

    /**
     * 显示角色当前状态
     */
    public void display(){
        System.out.println("当前状态：【血量："+getBlood()+", 蓝量："+getBlue()+"】");
    }

    /**
     * 存档
     * @return Memento
     */
    public Memento saveMemento(){
        return new Memento(getBlood(),getBlue());
    }

    /**
     * 回复存档
     * @param memento memento
     */
    public void restoreMemento(Memento memento) {
        this.blood = memento.getBlood();
        this.blue = memento.getBlue();
    }
}
