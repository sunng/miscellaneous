package self.sunng.miscellaneous;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * Created by abc on 15/9/2.
 */
public class Test {
    public static void main1(String[] args) throws Exception {
        File f = new File("/Users/abc/Desktop/2015-08-17.sgsdkvh.st15.number.log");
        InputStreamReader read = new InputStreamReader(
                new FileInputStream(f), "UTF-8");
        BufferedReader reader = new BufferedReader(read);
        String line;
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countT = 0;
        try {
            while ((line = reader.readLine()) != null) {
                if(StringUtils.isEmpty(line)) {
                    continue;
                }
                String[] ss = line.split(",");
                if(ss.length != 2 || !StringUtils.isNumeric(ss[0]) || !StringUtils.isNumeric(ss[1])) {
                    continue;
                }
                int h0 = Integer.parseInt(ss[0]);
                int h1 = Integer.parseInt(ss[1]);

                countT++;

                float rate = (float)h0 / (float)h1;
                if(rate == 0.6) {
                    countA++;
                }
                if(rate < 0.33) {
                    countB++;
                } else if (rate < 0.66) {
                    countC++;
                } else {
                    countD++;
                }
            }
            reader.close();
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(countA);
        System.out.println(countB);
        System.out.println(countC);
        System.out.println(countD);
        System.out.println(countT);
    }

    public static void main(String[] args) {
        String mobileNumber = "15801542583";
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++) {
            String result = mobileNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
//            System.out.println(result);
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();

        for(int i = 0; i < 10000; i++) {
            String result = mobileNumber.substring(0, 3);
            result = result.concat("****");
            result = result.concat(mobileNumber.substring(7));
//            System.out.println(result);
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();

        for(int i = 0; i < 10000; i++) {
            String[] split = mobileNumber.split("");
            for (int num = 0; num < split.length ; num ++){
                if(num>2 && num <7){
                    split[num] = "*";
                }
            }
            String phoneNumber = "";
            for (int num = 0; num < split.length ; num ++){
                phoneNumber = phoneNumber + split[num];
            }

//            System.out.println(phoneNumber);
        }


        System.out.println(System.currentTimeMillis() - start);

    }

}
