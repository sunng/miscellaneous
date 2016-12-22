package self.sunng.miscellaneous;

import java.io.IOException;

/**
 * Created by sunxiaodong on 2016/12/14.
 */
public class ShutDownHook {

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("shut down");
            }
        });

        System.out.println(System.in.read());
    }
}
