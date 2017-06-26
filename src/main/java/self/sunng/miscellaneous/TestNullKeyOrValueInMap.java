package self.sunng.miscellaneous;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sunxiaodong on 2017/2/13.
 */
public class TestNullKeyOrValueInMap {

    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        // hashtable.put(null, "1");
        // hashtable.put("1", null);
        hashtable.put("1", "1");

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        // concurrentHashMap.put(null, "1");
        // concurrentHashMap.put("1", null);
        concurrentHashMap.put("1", "1");

        TreeMap treeMap = new TreeMap();
        // treeMap.put(null, "1");
        treeMap.put("1", null);
        treeMap.put("1", "1");

        HashMap hashMap = new HashMap();
        hashMap.put(null, "1");
        hashMap.put("1", null);
        hashMap.put("1", "1");
    }
}
