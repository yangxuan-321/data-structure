package org.chiefdata.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Kevin
 * @Title : SetTest
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/1 12:57
 * @Modifyed By :
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new LinkedListSet<String>(String::compareTo);
        List<String> list = Arrays.asList("1", "1", "5", "2", "5", "8");
        for (String s: list) {
            set.add(s);
        }
        // 不重复数据个数
        System.out.println(set.getSize());
    }
}
