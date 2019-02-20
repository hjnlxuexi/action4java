package hj.action.DesignPatterns.Command.example.television.command;

import hj.action.DesignPatterns.Command.example.television.Television;

/**
 * <p>Title : 打开电视</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-14 </p>
 *
 * @author : hejie
 */
public class OpenTvCmd implements Command {
    private Television tv;

    public OpenTvCmd(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.open();
    }
}
