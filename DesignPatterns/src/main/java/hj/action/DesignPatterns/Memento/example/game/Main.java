package hj.action.DesignPatterns.Memento.example.game;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-20 </p>
 *
 * @author : hejie
 */
public class Main {

    public static void main(String[] args) {
        Role role = new Role(95,85);
        //显示当前状态
        role.display();

        System.out.println("马上打boss，存个档...");
        //存档
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(role.saveMemento());

        role.setBlood(10);
        role.setBlue(5);
        role.display();
        System.out.println("要GG，打不过，恢复存档，重新来...");

        role.restoreMemento(caretaker.getMemento());
        role.display();

    }
}
