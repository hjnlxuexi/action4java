package hj.action.DesignPatterns.Command.example.television;

import hj.action.DesignPatterns.Command.example.television.command.Command;

/**
 * <p>Title : 遥控器</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-14 </p>
 *
 * @author : hejie
 */
public class Controller {

    private Command openCmd;
    private Command closeCmd;
    private Command changeChannelCmd;

    public Controller(Command openCmd, Command closeCmd, Command changeChannelCmd) {
        this.openCmd = openCmd;
        this.closeCmd = closeCmd;
        this.changeChannelCmd = changeChannelCmd;
    }

    public void openTv() {
        openCmd.execute();
    }

    public void closeTv() {
        closeCmd.execute();
    }

    public void ChangeChannel() {
        changeChannelCmd.execute();
    }

    /**
     * 命令模式，需要有恢复功能
     */
    public void redo() {}

    /**
     * 撤销功能
     */
    public void undo() {}
}
