package org.chiefdata.leetcode;

/**
 * @author : Kevin
 * @Title : Q308
 * @ProjectName data-structure
 * @Description : 如果 是 数据 发生更新 用预处理的方式 就会有一定的确定  我们 要实时去更新一批 之和。
 * 如果数据量比较大，这个 之和 的数量也是很大的。
 * @Time : Created in 2020/3/4 23:37
 * @Modifyed By :
 */
public class Q308 {
    public static void main(String[] args) {

    }
}

/**
 * 解题思路2: 采用预处理方式 来解决这个问题
 */
class NumArray11 {
    private int[] data;
    private int[] sum;  // sum[i]存储前i个元素和，sum[0] = 0;
    // sum[i]存储nums[0...i-1]的和
    public NumArray11(int[] nums) {
        if (nums.length > 0){
            data = nums;
            sum = new int[data.length + 1];
            sum[0] = 0;
            for (int i = 0; i < data.length; i++) {
                sum[i+1] = sum[i] + data[i];
            }
        }
    }

    /**
     * 看的出来update是一个O(n)的复杂度，因此可以使用 线段树的 数据结构了。
     * @param index
     * @param val
     */
    public void update(int index, int val){
        data[index] = val;
        for (int i = index + 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + data[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        // 之和 - 之和 就是区间
        return sum[j+1] - sum[i];
    }
}
