package self.sunng.miscellaneous.javawithgroovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;

/**
 * Created by abc on 15/11/26.
 */
public class HelloWorld {

    public static void sayHello(String name) {
        System.out.println("hello world, i'm " + name);
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException {
        // method 1
        HelloGroovy.sayHello("java");

        // method 2
        ClassLoader parent = ClassLoader.getSystemClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        // cache the class object
        Class< ?> clazz = loader.parseClass(new File("test/src/main/java/self/sunng/javawithgroovy/HelloGroovyScript.groovy"));
        GroovyObject clazzObj = (GroovyObject)clazz.newInstance();
        clazzObj.invokeMethod("sayHello", "java");
    }
}
