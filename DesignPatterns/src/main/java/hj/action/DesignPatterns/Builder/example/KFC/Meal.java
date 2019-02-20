package hj.action.DesignPatterns.Builder.example.KFC;

/**
 * <p>Title : 套菜</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
public class Meal {

    private String food;//吃的

    private String drink;//喝的

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String toString(){
        return "套餐内容："+food+"，"+drink;
    }
}
