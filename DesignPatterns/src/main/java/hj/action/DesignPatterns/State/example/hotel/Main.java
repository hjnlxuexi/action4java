package hj.action.DesignPatterns.State.example.hotel;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-15 </p>
 *
 * @author : hejie
 */
public class Main {

    public static void main(String[] args) {
        //一个酒店两个房间
        Room room1 = new Room();
        Room room2 = new Room();


        room1.book();//预订
        room1.checkIn();//入驻
        room1.checkOut();//退房

        System.out.println("--------------------");

        room2.book();//预订
        room2.unsubscribe();//退订
        room2.book();//预订
        room2.book();//重复预订
    }
}
