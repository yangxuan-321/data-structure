package org.chiefdata.tree.binart_avl_tree.node;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin
 * @Title: TestEqual
 * @ProjectName data-structure
 * @Description: TODO
 * @date 2019/6/12 18:09
 */
public class TestEqual {
    public static void main(String[] args) {
        System.out.println("gdejicbegh".hashCode());
        System.out.println("hgebcijedg".hashCode());
        Map<String, String> map = new HashMap<String, String>();
        map.put("gdejicbegh", "1");
        map.put("hgebcijedg", "2");
        System.out.println(map);
        System.out.println("gdejicbegh".hashCode() == "hgebcijedg".hashCode());
    }
}
