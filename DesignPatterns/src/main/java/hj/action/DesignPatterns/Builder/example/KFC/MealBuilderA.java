package hj.action.DesignPatterns.Builder.example.KFC;

/**
 * <p>Title : 套餐A建造者</p>
 * <p>Description : </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
public class MealBuilderA extends MealBuilder {

    public MealBuilderA(Meal meal) {
        super(meal);
    }

    @Override
    public void buildFood() {
        Meal meal = getMeal();
        meal.setFood("汉堡");
        System.out.println("套餐A-吃的，done");
    }

    @Override
    public void buildDrink() {
        Meal meal = getMeal();
        meal.setDrink("大可乐");
        System.out.println("套餐A-喝的，done");
    }
}
