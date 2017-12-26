package self.sunng.miscellaneous.innerclass;

public class LocalInner {

    public void execute() {
        final int a = 1;
        /**
         * 创建局部内部类
         */
        class InnerClass {
            public void execute() {
                System.out.println("LocalInner Class");

                //局部内部类只能访问final类型的变量
                System.out.println(a);
            }
        }
        //只能在所在方法区域创建
        new InnerClass().execute();
    }
}
