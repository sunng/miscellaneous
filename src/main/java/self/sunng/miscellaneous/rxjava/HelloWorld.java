package self.sunng.miscellaneous.rxjava;

import rx.Observable;

/**
 * Created by abc on 15/11/26.
 */
public class HelloWorld {
    public static void main(String[] args) {
        hello("sun", "xiaodong");
    }

    public static void hello(String... names) {
        Observable.from(names).subscribe(s -> System.out.println(s));
        Observable.from(names).map(s -> s.hashCode()).subscribe(s -> System.out.println(s));
    }
}
