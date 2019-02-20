package hj.action.DesignPatterns.State.example.hotel;

/**
 * <p>Title : 房间：Context类</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-15 </p>
 *
 * @author : hejie
 */
public class Room {

    private FreeState freeState;
    private BookedState bookedState;
    private CheckInState checkInState;

    private State state;//房间状态

    public Room() {
        freeState = new FreeState(this);
        bookedState = new BookedState(this);
        checkInState = new CheckInState(this);
        this.state = freeState;
    }

    /**
     * 预订房间
     */
    public void book() {
        state.book();
    }

    /**
     * 入驻
     */
    public void checkIn() {
        state.checkIn();
    }

    /**
     * 退订
     */
    public void unsubscribe() {
        state.unsubscribe();
    }

    /**
     * 退房
     */
    public void checkOut() {
        state.checkOut();
    }

    /**
     * 设置状态
     * @param state 状态
     */
    public void setState(State state) {
        this.state = state;
    }

    public FreeState getFreeState() {
        return freeState;
    }

    public BookedState getBookedState() {
        return bookedState;
    }

    public CheckInState getCheckInState() {
        return checkInState;
    }
}
