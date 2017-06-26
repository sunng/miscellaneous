package self.sunng.miscellaneous;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunxiaodong on 2017/2/13.
 */
public class TestRemoveItemFromListInFor {

    public static void main(String[] args) throws Exception {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            // 1 ok, 2 ConcurrentModificationException
            if ("1".equals(temp)) {
                a.remove(temp);
            }
        }
    }
}
