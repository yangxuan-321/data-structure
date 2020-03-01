package org.chiefdata.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * @author : Kevin
 * @Title : Q349
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/29 18:26
 * @Modifyed By :
 */
public class Q349 {
    public static void main(String[] args) {
        int[] nums1 = new int[2];
        nums1[0] = 10000;
        nums1[1] = 1000000;

        int[] nums2 = new int[2];
        nums2[0] = 10000;
        nums2[1] = 1000000;

        int[] intersection = new Solution().intersection(nums1, nums2);
        for (int i = 0; i < intersection.length; i++) {
            System.out.print(intersection[i] + "->");
        }
    }
}

class Solution{
    public int[] intersection(int[] nums1, int[] nums2){
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (Integer num: nums1) {
            set.add(num);
        }

        List<Integer> list = new ArrayList<Integer>();
        for (int num: nums2) {
            if (set.contains(num)){
                list.add(num);
                set.remove(num);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }
}
