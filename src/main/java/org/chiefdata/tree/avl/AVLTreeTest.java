package org.chiefdata.tree.avl;

import org.chiefdata.tree.trie.Trie;
import org.chiefdata.util.FileOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kevin
 * @Title : AVLTest
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/7 16:03
 * @Modifyed By :
 */
public class AVLTreeTest {
    public static void main(String[] args){
        System.out.println("Pride and Prejudice");

        List<String> words = new ArrayList<>();
        if(FileOperation.readFile(AVLTreeTest.class.getResource("/data/pride-and-prejudice.txt").getFile(), words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());

            for(String word: words){
                map.remove(word);
                if(!map.isBST() || !map.isBalanced())
                    throw new RuntimeException();
            }
            System.out.println("success");
        }

        System.out.println();
    }
}
