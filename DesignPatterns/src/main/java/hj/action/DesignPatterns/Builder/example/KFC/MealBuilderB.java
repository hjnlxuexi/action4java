package hj.action.DesignPatterns.Builder.example.KFC;

/**
 * <p>Title : 套餐B建造者</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
public class MealBuilderB extends MealBuilder {

    public MealBuilderB(Meal meal) {
        super(meal);
    }

    @Override
    public void buildFood() {
        Meal meal = getMeal();
        meal.setFood("大薯条");
        System.out.println("套餐B-吃的");
    }

    @Override
    public void buildDrink() {
        Meal meal = getMeal();
        meal.setDrink("中可乐");
        System.out.println("套餐B-喝的");
    }
}
