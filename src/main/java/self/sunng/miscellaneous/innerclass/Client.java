package self.sunng.miscellaneous.innerclass;

public class Client {
    public static void main(String[] args) {
        StaticInner.InnerClass staticInnerClass = new StaticInner.InnerClass();
        staticInnerClass.execute();

        MemberInner memberInner = new MemberInner();
        memberInner.execute();
        MemberInner.InnerClass memberInnerClass = new MemberInner().new InnerClass();
        memberInnerClass.execute();

        LocalInner localInner = new LocalInner();
        localInner.execute();

        AnonymousInner anonymousInner = new AnonymousInner() {
            @Override
            public void execute() {
                super.execute();
                System.out.println("AnonymousInner$1 execute");
            }
        };
        anonymousInner.execute();
    }
}
