package hj.action.DesignPatterns.Builder.example.KFC;

/**
 * <p>Title : </p>
 * <p>Description : </p>
 * <p>Date : 2019-02-16 </p>
 *
 * @author : hejie
 */
public class Main {

    public static void main(String[] args) {
        // 服务员来个套餐A
        KFCWaiter waiter = new KFCWaiter(new MealBuilderA(new Meal()));

        // 算了，还是套餐B吧
        waiter.setMealBuilder(new MealBuilderB(new Meal()));

        Meal meal = waiter.construct();

        System.out.println(meal);
    }
}
