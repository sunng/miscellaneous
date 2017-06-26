package self.sunng.miscellaneous;

/**
 * Created by sunxiaodong on 2016/12/22.
 * 最大公约数 最小公倍数
 */
public class ZDGY_ZXGB {

    public static void main(String[] args) {

        // System.out.println(getBigDiv(40, 56));
        // System.out.println(getSmallMul(20, 12));
        int[] num = {12, 20, 8, 3, 7, 38};
        System.out.println(getMoreBigDiv(num, 6));
        System.out.println(getMoreSmallMul(num, 6));
    }

    // 求两个数的最大公约数
    private static int getBigDiv(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getBigDiv(b, a % b);
    }

    // 求两个数的最小公倍数
    private static int getSmallMul(int a, int b) {
        return (a * b) / getBigDiv(a, b);
    }

    // 求多个数的最大公约数
    private static int getMoreBigDiv(int num[], int n) {
        if (n == 1) {
            return num[n - 1];
        }
        return getBigDiv(num[n - 1], getMoreBigDiv(num, n - 1));
    }

    // 求多个数的最小公倍数
    private static int getMoreSmallMul(int num[], int n) {
        if (n == 1) {
            return num[n - 1];
        }
        return getSmallMul(num[n - 1], getMoreSmallMul(num, n - 1));
    }
}
