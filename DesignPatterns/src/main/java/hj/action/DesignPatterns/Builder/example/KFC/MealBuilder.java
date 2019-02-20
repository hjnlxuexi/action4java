package hj.action.DesignPatterns.Builder.example.KFC;

/**
 * <p>Title : 套餐建造者</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
public abstract class MealBuilder {
    private Meal meal;

    public MealBuilder(Meal meal) {
        this.meal = meal;
    }

    public abstract void buildFood();

    public abstract void buildDrink();

    public Meal getMeal(){
        return this.meal;
    }

}
