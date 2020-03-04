package org.chiefdata.tree.segment;

import com.google.common.base.Joiner;

import java.util.function.BiFunction;

/**
 * @author : Kevin
 * @Title : SegmentTree
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/4 15:59
 * @Modifyed By :
 */
public class SegmentTree<E> {
    // 整棵树存储的元素
    private E[] tree;
    // 被树组织的基础元素
    private E[] data;

    private BiFunction<E, E, E> mergeFunc;

    public void setMergeFunc(BiFunction<E, E, E> mergeFunc) {
        this.mergeFunc = mergeFunc;
    }

    public SegmentTree(E[] arr, BiFunction<E, E, E> mergeFunc){
        this.mergeFunc = mergeFunc;
        // 存储 基础元素
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        // 需要组成线段树 的元素
        tree = (E[])new Object[arr.length * 4];

        // 初始化的时候 左边界 是0 有边界 是数据数组的最大值
        buildSegmentTree(0, 0, data.length-1);
    }

    /**
     * 在 treeIndex的位置 创建 表示区间[l, r]的线段树
     *
     * @param treeIndex treeIndex就代表 当前在树中 在该位置的 节点 treeIndex是在 数组中的位置。
     * @param l         l 代表区间的左边界
     * @param r         r 代表区间的右边界
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 当 区间左右边界 相等时, 就代表当前和这个元素 只用计算一个值，就直接 将其返回即可。
        if (l == r){
            tree[treeIndex] = data[l]; //或者 tree[treeIndex] = data[r]
            return;
        }

        // 如果l < r
        int leftTreeIndex = leftChild(treeIndex);       //
        int rightTreeIndex = rightChild(treeIndex);     //

//        System.out.print(" tree:" + treeIndex);
//        System.out.println(" l:" + leftTreeIndex + "," + " r:" + rightTreeIndex);

        // 计算 l 和 r 的中间位置
        int mid = l + (r - l) / 2; // 因为怕之和太大所以这样做 int mid = (l + r) / 2;
        // 递归去计算 treeIndex 的左儿子和右儿子的值
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 这个值 是 与业务 紧密相关，如果此处为求和 ， 具体是求和 还是求大小值。 都是业务自己定义的。
        tree[treeIndex] = this.mergeFunc.apply(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2 * index + 2;
    }

    // 返回线段树 区间[L, R]的值
    public E query(int queryL, int queryR){
        // 边界判断
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR){
            throw new IllegalArgumentException("l or r out of bound...");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 以 treeIndex为根的 线段树中的 [l, r] 的范围里，
     * 搜索区间[queryL....queryR]的 值(和/最大最小，反正就是自己关系的 这个区间的一个统计值)
     * 改进：其实可以将 int treeIndex, int l, int r 封装一下
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        // 边界条件，如果用户 关注的 queryL及queryR 与 当前 treeIndex索引的节点里面存储的[l, r]的 范围 相等，则代表
        // 已经 完全匹配到 该区间了，返回该节点的值 即可
        if (l == queryL && r == queryR){
            return tree[treeIndex];
        }

        // 区间的中间值
        int mid = l + (r - l)/2;
        // 计算出 左孩子和右孩子 决定去左孩子下面的区间找 还是 右孩子下面区间中去找
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1){
            // [queryL, queryR] 是我们关注的区间，也就是我们要去查的区间。而[l, r]是代表 我们要在 [l, r]中能否找到 [queryL, queryR]
            // 如果 关注的 区间的 左侧 大于 [mid + 1](mid+1的意思是我们将[l, r]区间一分为二，
            // 如果queryL即最左边都在我们要查询区间的 右边 那就直接去右边找)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }else if (queryR <= mid){
            // 同上取反
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }else {
            // 一部分落在 左孩子。 一部分落在右孩子
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            // 融合操作
            return mergeFunc.apply(leftResult, rightResult);
        }
    }

    @Override
    public String toString() {
        return Joiner.on(",").skipNulls().join(tree);
    }
}
