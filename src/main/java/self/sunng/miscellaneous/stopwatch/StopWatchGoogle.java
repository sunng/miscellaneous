package self.sunng.miscellaneous.stopwatch;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by sunxiaodong on 2017/2/6.
 */
public class StopWatchGoogle {

    public static void main(String[] args) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Thread.sleep(1000);

        stopwatch.stop();

        System.out.println(stopwatch.elapsed(TimeUnit.MICROSECONDS));
    }
}
