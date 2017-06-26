package self.sunng.miscellaneous.stopwatch;

import org.apache.commons.lang3.time.StopWatch;

/**
 * Created by sunxiaodong on 2017/2/6.
 */
public class StopWatchCommonLang3 {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Thread.sleep(1000);

        stopWatch.stop();

        System.out.println(stopWatch.getTime());
    }
}
