package hj.action.DesignPatterns.Command.example.television;

/**
 * <p>Title : 电视机</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-14 </p>
 *
 * @author : hejie
 */
public class Television {

    public void open() {
        System.out.println("Open television ...");
    }

    public void close() {
        System.out.println("Close television ...");
    }

    public void changeChannel() {
        System.out.println("Change channel ...");
    }
}
