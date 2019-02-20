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
public class BookedState implements State {

    private Room room;

    public BookedState(Room room) {
        this.room = room;
    }

    @Override
    public void book() {
        throw new RuntimeException("预订状态，非法预订");
    }

    @Override
    public void checkIn() {
        System.out.println("入驻成功...");
        //更新状态
        room.setState(room.getCheckInState());
    }

    @Override
    public void unsubscribe() {
        System.out.println("退订成功...");
        //更新状态
        room.setState(room.getFreeState());
    }

    @Override
    public void checkOut() {
        throw new RuntimeException("预订状态，非法退房");
    }
}
