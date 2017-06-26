package self.sunng.miscellaneous;

/**
 * Created by abc on 15/9/2.
 */
public class TestMastPhoneIn3Ways {

    public static void main1(String[] args) {
        String mobileNumber = "15801542583";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String result = mobileNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            // System.out.println(result);
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            String result = mobileNumber.substring(0, 3);
            result = result.concat("****");
            result = result.concat(mobileNumber.substring(7));
            // System.out.println(result);
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            String[] split = mobileNumber.split("");
            for (int num = 0; num < split.length; num++) {
                if (num > 2 && num < 7) {
                    split[num] = "*";
                }
            }
            String phoneNumber = "";
            for (int num = 0; num < split.length; num++) {
                phoneNumber = phoneNumber + split[num];
            }

            // System.out.println(phoneNumber);
        }


        System.out.println(System.currentTimeMillis() - start);

    }

}
