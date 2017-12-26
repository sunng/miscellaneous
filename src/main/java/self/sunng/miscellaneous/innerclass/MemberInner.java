package self.sunng.miscellaneous.innerclass;

public class MemberInner {
    private int a = 1;

    public void execute() {
        InnerClass innerClass = this.new InnerClass();
    }

    public class InnerClass {

        private int a = 2;

        public void execute() {
            System.out.println(this.a);
            System.out.println(MemberInner.this.a);
        }
    }
}
