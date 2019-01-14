package hj.action.basics;

/**
 * <p>Title : 记录Java基本类型</p>
 * <p>Description :
 *
 * byte：字节，8位，最小数据单位，            -128 ~ 127
 *
 * char：字符，16位，是整数类型，             0 ~ 2^16-1
 * short：短整型，16位，                    -2^15 ~ 2^15-1
 * int：整数型，32位，                      -2^31 ~ 2^31-1
 * long：长整型，64位，                     -2^63 ~ 2^63-1
 *
 * float：浮点型，32位，                    1 位符号位，8 位指数，23 位有效尾数；  因此只能保证23位的精度
 * double：双精度型，64位，                 1 位符号位，11 位指数，52 位有效尾；   能保证52位的精度
 * 浮点数与双精度不精确的原因：
 *  十进制数字转换为二进制数字的过程：
 *      整数部分：①、n/2=x，②、取余，③、x/2=y，y=0；④二进制结果为，取余数字的倒序；
 *      小数部分：①、0.n * 2 = x，②、取x的整数部分，③小数部分再执行①，直到小数消失；④、取整的数字连起来就是二进制结果。
 *
 *
 * boolean：布尔型。
 *
 * </p>
 * <p>Date : 2018/12/22 </p>
 *
 * @author : hejie
 */
public class BasicType {

    /**
     * 形参与实参
     * @param i
     */
    private static void methodTest(int i){
        for (int j = 0; j < 10 ; j++) {
            i++;
        }
    }
    private static void methodTest(Integer i){
        i=10;
    }

    public static void main(String[] args) {
        int i = 0;
        methodTest(i);

        methodTest(new Integer(i));
        System.out.println(i);



        float f = 1e-23f;
        double d = 1e-53d;
        System.out.println(Double.MAX_VALUE+","+Double.MIN_VALUE);
        System.out.println(Float.MAX_VALUE+","+Float.MIN_VALUE);
    }
}
