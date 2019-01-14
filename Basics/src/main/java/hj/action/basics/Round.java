package hj.action.basics;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>Title : 四舍五入</p>
 * <p>Description :
 * <p>
 * Java支持的舍入法 RoundingMode ：
 * 1、 ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。
 * 2、 ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。
 * 3、 ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于ROUND_UP，若为负数，舍入行为类似于ROUND_DOWN。Math.round()方法就是使用的此模式。
 * 4、 ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于ROUND_DOWN；若为负数，舍入行为类似于ROUND_UP。
 * 5、 HALF_UP：最近数字舍入(5进)。向绝对值最大的方向。这是我们最经典的四舍五入。
 * 6、 HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。
 * 7、 HAIL_EVEN：银行家舍入法：
         舍去位的数值小于5时，直接舍去。
         舍去位的数值大于5时，进位后舍去。
         当舍去位的数值等于5时，若5后面还有其他非0数值，则进位后舍去，若5后面是0时，则根据5前一位数的奇偶性来判断，奇数进位，偶数舍去
 * <p>
 * <p>
 * </p>
 * <p>Date : 2018/12/20 </p>
 *
 * @author : hejie
 */
public class Round {

    public static void main(String[] args) {
        Double f = -111231.585;
        //必须转换为string，不然会出现精度bug
        String s = f.toString();
        System.out.println(new BigDecimal(s).setScale(2, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal(s).setScale(2, RoundingMode.HALF_EVEN));
    }
}
