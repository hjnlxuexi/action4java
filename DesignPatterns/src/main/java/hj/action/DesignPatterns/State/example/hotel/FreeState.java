package hj.action.DesignPatterns.State.example.hotel;

/**
 * <p>Title : 空闲状态</p>
 * <p>Description :
 *
 * 1、可以预订
 * 2、可以直接入驻
 *
 * </p>
 * <p>Date : 2019-02-15 </p>
 *
 * @author : hejie
 */
public class FreeState implements State {

    private Room room;

    public FreeState(Room room) {
        this.room = room;
    }


    @Override
    public void book() {
        System.out.println("已预订...");
        //更新状态
        room.setState(room.getBookedState());
    }

    @Override
    public void checkIn() {
        System.out.println("已入驻...");
        //更新状态
        room.setState(room.getCheckInState());
    }

    @Override
    public void unsubscribe() {
        throw new RuntimeException("空闲状态，非法退订");
    }

    @Override
    public void checkOut() {
        throw new RuntimeException("空闲状态，非法退房");
    }
}
