package org.chiefdata.leetcode;

import org.chiefdata.tree.segment.SegmentTree;

/**
 * @author : Kevin
 * @Title : Q303
 * @ProjectName data-structure
 * @Description : 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * 示例：
 *
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 *
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 * @Time : Created in 2020/3/4 23:08
 * @Modifyed By :
 */
public class Q303 {
    public static void main(String[] args) {
        System.out.println(new NumArray1(new int[]{-2, 0, 3, -5, 2, -1}).sumRange(0, 2));;
    }
}

/**
 * 解题思路1: 使用线段树
 */
class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0){
            Integer[] arrs = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                arrs[i] = nums[i];
            }

            segmentTree = new SegmentTree<Integer>(arrs, (e1, e2)->e1 + e2);
        }
    }

    public int sumRange(int i, int j) {
        if (null == segmentTree){
            return 0;
        }

        return segmentTree.query(i, j);
    }
}

/**
 * 解题思路2: 因为数据的不可变性，采用预处理方式 来解决这个问题
 */
class NumArray1 {

    private int[] sum;  // sum[i]存储前i个元素和，sum[0] = 0;
                        // sum[i]存储nums[0...i-1]的和
    public NumArray1(int[] nums) {
        if (nums.length > 0){
            sum = new int[nums.length + 1];
            sum[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                sum[i+1] = sum[i] + nums[i];
            }
        }
    }

    public int sumRange(int i, int j) {
        // 之和 - 之和 就是区间
        return sum[j+1] - sum[i];
    }
}

