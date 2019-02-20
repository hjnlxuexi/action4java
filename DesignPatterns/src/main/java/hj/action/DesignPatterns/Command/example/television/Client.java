package hj.action.DesignPatterns.Command.example.television;


import hj.action.DesignPatterns.Command.example.television.command.ChangeChannelCmd;
import hj.action.DesignPatterns.Command.example.television.command.CloseTvCmd;
import hj.action.DesignPatterns.Command.example.television.command.OpenTvCmd;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-14 </p>
 *
 * @author : hejie
 */
public class Client {

    public static void main(String[] args) {
        Television tv = new Television();

        OpenTvCmd openTvCmd = new OpenTvCmd(tv);
        CloseTvCmd closeTvCmd = new CloseTvCmd(tv);
        ChangeChannelCmd changeChannelCmd = new ChangeChannelCmd(tv);

        Controller controller = new Controller(openTvCmd, closeTvCmd, changeChannelCmd);

        controller.openTv();
        controller.ChangeChannel();
        controller.closeTv();

    }
}
