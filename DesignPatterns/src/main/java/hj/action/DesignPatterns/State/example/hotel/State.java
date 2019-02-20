package hj.action.DesignPatterns.State.example.hotel;

/**
 * <p>Title : 状态接口类</p>
 * <p>Description :
 *
 *  所有状态提供4个行为：
 *      1、预订
 *      2、入驻
 *      3、退订
 *      4、退房
 *
 *
 * </p>
 * <p>Date : 2019-02-15 </p>
 *
 * @author : hejie
 */
public interface State {

    void book();//预订

    void checkIn();//入驻

    void unsubscribe();//退订

    void checkOut();//退房

}
