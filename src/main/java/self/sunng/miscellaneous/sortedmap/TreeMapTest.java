package self.sunng.miscellaneous.sortedmap;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by sunxiaodong on 2016/12/30.
 */
public class TreeMapTest {

    public static void main(String[] args) {
        TreeMap<String, Object> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Integer i1 = Integer.parseInt(o1);
                Integer i2 = Integer.parseInt(o2);
                return i1.compareTo(i2);
            }
        });

        treeMap.put("1", "1");
        treeMap.put("4", "4");
        treeMap.put("3", "3");
        treeMap.put("2", "2");

        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());

        System.out.println(treeMap.floorKey("5"));
        System.out.println(treeMap.ceilingKey("0"));

        System.out.println(treeMap.higherKey("1"));
        System.out.println(treeMap.lowerKey("4"));

        treeMap.pollFirstEntry();
        System.out.println(treeMap.firstKey());
    }
}
