package self.sunng.miscellaneous;

import java.util.Calendar;

/**
 * Created by abc on 16/7/15.
 */
public class TestGetMillionTime {

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());

        Calendar.getInstance().getTimeInMillis();

        System.out.println(System.currentTimeMillis());
        // System.out.println(Calendar.getInstance().getTimeInMillis());
        for(int i = 0; i < 10000; i++) {
            Calendar.getInstance().getTimeInMillis();
        }
        System.out.println(System.currentTimeMillis());
        // System.out.println(Calendar.getInstance().getTimeInMillis());
        for(int i = 0; i < 10000; i++) {
            System.currentTimeMillis();
        }
        System.out.println(System.currentTimeMillis());
        // System.out.println(Calendar.getInstance().getTimeInMillis());
    }
}
