package self.sunng.miscellaneous.javawithgroovy

/**
 * Created by abc on 15/11/26.
 */

class HelloGroovyScript {
    public static void sayHello(String name) {
        println("hello groovy script, i'm " + name);
    }

    public static void main(String[] args) {
        HelloWorld.sayHello("groovy script");
    }
}
