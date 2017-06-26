package self.sunng.miscellaneous.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by sunxiaodong on 2017/2/14.
 * guava 实现的令牌桶
 */
public class TestRateLimiter {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(5);
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
    }
}
