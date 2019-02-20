package hj.action.DesignPatterns.Builder.example.KFC;

/**
 * <p>Title : 服务员</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
public class KFCWaiter {
    private MealBuilder mealBuilder;

    public KFCWaiter(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    public Meal construct(){
        mealBuilder.buildFood();
        mealBuilder.buildDrink();
        return mealBuilder.getMeal();
    }

    public void setMealBuilder(MealBuilder mealBuilder){
        this.mealBuilder = mealBuilder;
    }
}
