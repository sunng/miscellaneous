package self.sunng.miscellaneous.lombok;

/**
 * Created by sunxiaodong on 16/10/11.
 */
public class Tester {

    public static void main(String[] args) {
        Bean b = new Bean();
        b.setSfield("s");
        b.setIfield(1);
        System.out.println(b.getSfield());
        System.out.println(b.getIfield());
    }
}
