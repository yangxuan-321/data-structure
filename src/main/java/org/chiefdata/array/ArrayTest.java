package org.chiefdata.array;

/**
 * @author : Kevin
 * @Title : ArrayTest
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/25 14:36
 * @Modifyed By :
 */
public class ArrayTest {
    // 数组最大的优点：快速查询
    // 数组最好运用于"索引有语义的情况"
    // 但并非所有有语义的索引都适用于数组
    public static void main(String[] args) {
        Array<Integer> array = new Array<Integer>();
        for (int i = 0; i < 10; i++) {
            array.add(i);
        }

        array.remove();

//        System.out.println(array);
//
//        Integer integer = array.find((i) -> i.intValue() == 3);
        System.out.println(array.toString());
    }

    public void timeDesc(){
        /**
         * 简单的时间复杂度分析
         * O(1), O(n), O(lgn), O(nlogn), O(n^2)
         * 大O描述的是算法的运行时间和输入数据之间的关系
         * 对于 sum() 方法来说 时间复杂度就是 O(n) 其中n是nums数组的个数
         * 那么 O(n) 也就是说，这个算法运行的时间的多少和nums数组元素个数呈线性关系的。线性关系 体现在 n^1 而并非是 n^2, n^3等等。
         * O(n) -> 算法和n呈线性关系。
         * 为什么要用大O，叫做O(n)呢? -> 忽略常数。实际时间 T = c1*n + c2, c1和c2其实意义不大，另一方面在有些情况下 也是 不可能算出来的。
         * 两个算法时间与复杂度的关系：
         *      T = 2*n + 2 -> O(n)
         *      T = 2000*n + 1000 -> O(n)
         *      T = 1*n*n + 0 -> O(n^2)
         *      T = 2*n*n + 300*n + 10 -> O(n^2)
         * 所以 当 n 大小不一定时，O(n^2) 并不一定 比 O(n) 慢。
         * 所以 O -> 被称为：渐进时间复杂度。实际上想描述的是 n -> ∞ n趋近于无穷的情况
         *
         * -----------------------------------------------------------------
         *
         * 分析
         *      add(e) -> O(1)
         *      add(index, e) -> O(n/2) -> O(n) ==== 严格计算需要一些概率论知识
         *      resize() -> O(n)
         *      remove(index) -> O(n/2) -> O(n)
         *      set(index, e) -> O(1)
         *      find() -> O(n/2) -> O(n)
         *
         * 时间复杂度要看 最坏情况。
         *
         * 其实 resize() 均摊计算的时间复杂度为： O((2*n + 1)/(n+1)) -> O(2) -> O(1)
         * 其实 均摊复杂度 比 渐进复杂度 更有意义。
         *
         * --复杂度震荡--
         * 当我们 此时 容器已满。我们使用 add(e) 方法，会调用resize()。当 我们 在调用 remove() 时又会调用resize()。再调用 add(e)....
         * 此时 时间复杂度 就会很麻烦。
         * 出现问题的原因：remove是resize过于着急(Eager)
         * 解决方案：lazy 。 当 size == capacity/4 时，才将capacity减半。
         *
         */
    }

    public static int sum(int[] nums){
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        return sum;
    }
}
