package hj.action.basics.String;

/**
 * <p>Title : 测试String的用法</p>
 * <p>Description :
 * 字符串拼接：+ < contact() < append()
 *
 * </p>
 * <p>Date : 2018/12/21 </p>
 *
 * @author : hejie
 */
public class StringTest {

    public static void main(String[] args) {
        //+
        long start_01 = System.currentTimeMillis();
        String a = "a";
        for(int i = 0 ; i < 100000 ; i++){
            a += "b";// 等同于：a = new StringBuilder(a).append(“b”).toString();  多了toString操作，所以慢
        }
        long end_01 = System.currentTimeMillis();
        System.out.println("  +   所消耗的时间：" + (end_01 - start_01) + "ms");

        //concat()
        long start_02 = System.currentTimeMillis();
        String c = "c";
        for(int i = 0 ; i < 100000 ; i++){
            c = c.concat("d"); // 内部会重新 new String(""); 所以会比较慢
        }
        long end_02 = System.currentTimeMillis();
        System.out.println("concat所消耗的时间：" + (end_02 - start_02) + "ms");

        //append
        long start_03 = System.currentTimeMillis();
        StringBuffer e = new StringBuffer("e");
        for(int i = 0 ; i < 100000 ; i++){
            e.append("d");
        }
        long end_03 = System.currentTimeMillis();
        System.out.println("append所消耗的时间：" + (end_03 - start_03) + "ms");
    }
}
