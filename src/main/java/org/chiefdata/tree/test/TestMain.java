package org.chiefdata.tree.test;

import org.chiefdata.tree.binart_sort_tree.BinSortTree;

import java.util.ArrayList;

/**
 * @author : Kevin
 * @Title : TestMain
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2019/5/23 22:56
 * @Modifyed By :
 */
public class TestMain {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>() {{
            add(62);
            add(88);
            add(58);
            add(47);
            add(35);
            add(73);
            add(51);
            add(99);
            add(37);
            add(93);
        }};
        BinSortTree<Integer> sortTree = new BinSortTree<Integer>();
//        sortTree.insert(list);
        for (int i = 0; i < list.size(); i ++){
            sortTree.insert(list.get(i));
        }
        System.out.println(sortTree.sortDisplay());
//        System.out.println(sortTree.testFindMostLeftNode().data);
        sortTree.delete(93);
        System.out.println(sortTree.sortDisplay());
    }
}
