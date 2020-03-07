package org.chiefdata.tree.trie;

import org.chiefdata.util.FileOperation;

import java.util.*;

/**
 * @author : Kevin
 * @Title : TrieTest
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/5 15:26
 * @Modifyed By :
 */
public class TrieTest {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        List<String> words = new ArrayList<String>();
        if(FileOperation.readFile(Trie.class.getResource("/data/pride-and-prejudice.txt").getFile(), words)) {

            long startTime = System.nanoTime();

            Set<String> set = new HashSet<String>();
            for (String word : words)
                set.add(word);

            for (String word : words)
                set.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.size());
            System.out.println("BSTSet: " + time + " s");

            // ---

            startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words)
                trie.add(word);

            for (String word : words)
                trie.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }
    }
}
