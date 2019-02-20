package hj.action.DesignPatterns.Memento.example.game;

/**
 * <p>Title : 负责人：保存存档</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-20 </p>
 *
 * @author : hejie
 */
public class Caretaker {
    Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
