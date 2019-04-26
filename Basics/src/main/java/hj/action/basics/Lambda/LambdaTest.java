package hj.action.basics.Lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <p>Title : lambda以及Stream</p>
 * <p>Description :
 *
 * 参考：
 *  https://www.cnblogs.com/franson-2016/p/5593080.html
 *  https://www.cnblogs.com/snowInPluto/p/5981400.html
 *
 * </p>
 * <p>Date : 2019-04-02 </p>
 *
 * @author : hejie
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

        //1、遍历
        for (String player : players) {
            System.out.println(player);
        }
        players.forEach(player -> System.out.println(player));
        players.forEach(System.out::println);



        //2、排序
        System.out.println("=============================");
        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });
        Arrays.sort(atp, (a,b)->a.compareTo(b));
        Arrays.sort(atp, String::compareTo);

        //3、Stream
        System.out.println("=============================");
        players.stream().filter(s -> s.startsWith("R"))
                .forEach(System.out::println);

        players.parallelStream().filter(s -> s.startsWith("R"))
                .forEach(System.out::println);
    }
}
