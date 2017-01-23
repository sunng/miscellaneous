package self.sunng.miscellaneous;

import java.math.BigDecimal;

/**
 * Created by sunxiaodong on 2016/12/23.
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        System.out.println(new BigDecimal(1.1));
        System.out.println(new BigDecimal("1.1"));

        BigDecimal bigDecimal = new BigDecimal("1.2345");
        System.out.println(bigDecimal);
        System.out.println(bigDecimal.add(new BigDecimal("1.2345")));
        System.out.println(bigDecimal.subtract(new BigDecimal("1.2")));
        System.out.println(bigDecimal.multiply(new BigDecimal("2.2")));
        System.out.println(bigDecimal.divide(new BigDecimal("2")));
    }
}
