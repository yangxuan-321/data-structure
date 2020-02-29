package org.chiefdata.tree.map;

import org.chiefdata.tree.bst.BST;

/**
 * @author : Kevin
 * @Title : BSTMapTest
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/29 16:04
 * @Modifyed By :
 */
public class BSTMapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new BSTMap<Integer, String>();
        map.add(5, "5");
        map.add(3, "3");
        map.add(6, "6");
        map.add(8, "8");
        map.add(4, "4");
        map.add(2, "2");
        ((BSTMap<Integer, String>) map).preQuery();
        // 中序 就是 排序的结果
        ((BSTMap<Integer, String>) map).middleQuery();
        ((BSTMap<Integer, String>) map).afterQuery();
    }
}
