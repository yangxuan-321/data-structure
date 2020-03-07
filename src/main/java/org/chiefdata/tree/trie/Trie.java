package org.chiefdata.tree.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author : Kevin
 * @Title : Trie
 * @ProjectName data-structure
 * @Description : trie字典树
 * @Time : Created in 2020/3/5 14:29
 * @Modifyed By :
 */
public class Trie {
    /**
     * Trie的根节点
     */
    private TrieNode root;

    /**
     * 容量大小
     */
    private int size;

    public Trie(){
        this.root = new TrieNode();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 将 和这个 单词 拆分成 一个个 字符串。来存入 Trie中
     * @param word
     */
    public void add(String word){
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                cur.next.put(c, new TrieNode());
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWord){
            cur.isWord = true;
            size ++;
        }
    }

    /**
     * 从Trie中查询一个单词
     * @param word
     * @return
     */
    public boolean contains(String word){
        TrieNode cur = root;
        // 从Trie中 顺着单词的字母一个一个接着往下找。
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode trieNode = cur.next.get(c);
            // 当某个单词找不到，就不存在该单词
            if (trieNode == null){
                return false;
            }
            cur = cur.next.get(c);
        }

        if (cur.isWord){
            return true;
        }

        return false;
    }

    /**
     * 查询 是否 存在Trie中有单词以Prefix为前缀
     * 一个单词也是这个单词的前缀
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix) {
        TrieNode cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }

            cur = cur.next.get(c);
        }

        return true;
    }

    /**
     * 带通配符的搜索 - 即模式匹配
     * @param regex
     * @return
     */
    public boolean searchByRegex(String regex){
        // 深度遍历的方式， 也可以使用广度遍历的方式(借助 队列)
        return searchByRegex(root, regex, 0);
    }

    private boolean searchByRegex(TrieNode node, String word, int index){
        if (index == word.length()){
            return node.isWord;
        }

        char c = word.charAt(index);
        if (c != '.'){
            if (node.next.get(c) == null){
                return false;
            }

            // 这一次 匹配成功了，匹配下一个
            return searchByRegex(node.next.get(c), word, index + 1);
        }else {
            // 如果是 . 通配符 就将该 map 里面的元素每个都 去 匹配下
            Set<Character> characters = node.next.keySet();
            for (Character character:characters) {
                if (searchByRegex(node.next.get(character), word, index + 1)){
                    return true;
                }
            }
            return false;
        }

    }
}

/**
 * 专门为英文设计
 */
class TrieNode{
    // 是否是一个单词
    public boolean isWord;

    // 多子树
    public Map<Character, TrieNode> next;

    public TrieNode(){
        this(false);
    }

    public TrieNode(boolean isWord){
        this.isWord = isWord;
        this.next = new HashMap<Character, TrieNode>();
    }
}

//class TrieNode<C>{
//    // 是否是一个单词
//    private boolean isWord;
//
//    private Map<C, TreeNode> next;
//}