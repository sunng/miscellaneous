package self.sunng.miscellaneous.innerclass;

public class StaticInner {
    private static int i = 0;
    private static void execute() {
        System.out.println("StaticInner execute " + i);
    }
    public static class InnerClass {
        private static int j = 1;
        public void execute() {
            StaticInner.execute();
            System.out.println("InnerClass execute " + i + "," + j);
        }
    }
}
