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
        BSTMap<Integer, String> map = new BSTMap<Integer, String>();
//        map.add(5, "5");
//        map.add(3, "3");
//        map.add(6, "6");
//        map.add(8, "8");
//        map.add(4, "4");
//        map.add(2, "2");
        map.add(28, "28");
        map.add(16, "16");
        map.add(30, "30");
        map.add(13, "13");
        map.add(22, "22");
        map.add(29, "29");
        map.add(42, "42");
        System.out.println("------前序递归------");
        map.preQuery();
        System.out.println("------前序非递归------");
        map.preQueryNoneDigui();
        System.out.println("------中序递归------");
        // 中序 就是 排序的结果
        map.middleQuery();
        System.out.println("------后序递归------");
        map.afterQuery();
        System.out.println("------广度优先(层序)遍历------");
        map.leverQuery();
    }
}
