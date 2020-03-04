package org.chiefdata.leetcode;

import java.util.*;

/**
 * @author : Kevin
 * @Title : Q347
 * @ProjectName data-structure
 * @Description : 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * @Time : Created in 2020/3/2 15:09
 * @Modifyed By :
 */
public class Q347 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        System.out.println(new Solution347().topKFrequent(nums, k));;
    }
}

class Solution347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0){
            return new ArrayList<Integer>();
        }
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (Integer num: nums) {
            Integer ele = map.get(num);
            if (null == ele){
                map.put(num, 1);
            }else {
                map.put(num, ele + 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();

        Queue<Map.Entry<Integer, Integer>> queue =
                new PriorityQueue<Map.Entry<Integer, Integer>>(k,
                        (e1, e2)->e2.getValue().compareTo(e1.getValue()));

        for (Map.Entry<Integer, Integer> entry: entries) {
            queue.add(entry);
        }

        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            res.add(queue.poll().getKey());
        }
        return res;
    }
}
