package hj.action.DesignPatterns.State.example.hotel;

/**
 * <p>Title : 已入驻状态</p>
 * <p>Description :
 *
 * 已入驻状态
 * 1、可以退房
 *
 * </p>
 * <p>Date : 2019-02-15 </p>
 *
 * @author : hejie
 */
public class CheckInState implements State {

    private Room room;

    public CheckInState(Room room) {
        this.room = room;
    }



    @Override
    public void book() {
        throw new RuntimeException("入驻状态，非法预订");
    }

    @Override
    public void checkIn() {
        throw new RuntimeException("入驻状态，非法入驻");
    }

    @Override
    public void unsubscribe() {
        throw new RuntimeException("入驻状态，非法退订");
    }

    @Override
    public void checkOut() {
        System.out.println("退房成功...");
        //更新状态
        room.setState(room.getFreeState());
    }
}
