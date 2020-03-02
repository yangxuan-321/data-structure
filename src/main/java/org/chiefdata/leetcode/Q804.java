package org.chiefdata.leetcode;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author : Kevin
 * @Title : Q804
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/1 14:27
 * @Modifyed By :
 */
public class Q804 {
    public static void main(String[] args) {
//        System.out.println("a".getBytes()[0]);
        System.out.println(new Solution2().uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));;
    }
}

class Solution2 {
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new TreeSet<String>();
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",
                ".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            String code = "";
            for (int j = 0; j < len; j++) {
                code += codes[words[i].charAt(j) - 'a'];
            }
            set.add(code);
        }

        return set.size();
    }
}
