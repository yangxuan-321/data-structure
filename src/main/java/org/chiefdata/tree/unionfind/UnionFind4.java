package org.chiefdata.tree.unionfind;

/**
 * @author : Kevin
 * @Title : QuickUnion
 * @ProjectName data-structure
 * @Description : 相较于 union find3 的实现 。unind find3 是通过 比较 两个节点 分别为 根的情况下。节点个数大小 来决定 怎么 合并。
 * 但是 节点个数大小 并不代表 以 该元素为根的 树的 深度大小。
 * 所以 union find4 是 通过 深度优化的
 * @Time : Created in 2020/3/7 10:26
 * @Modifyed By :
 */
public class UnionFind4 implements UnionFind {

    private int[] parent;
    // sz[i] 表示 以 i 为根的集合中元素个数
    private int[] nodeTreeRank;


    public UnionFind4(int size){
        parent = new int[size];
        nodeTreeRank = new int[size];

        // 初始化时，让每一个 节点 的父亲 指向自己本身。
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            nodeTreeRank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 相较于 3 实现，4 的 实现 将会 在此处 有变化。即 如果元素1 ele1 元素2 ele2合并，
     * 则 改进是： 将 以ele为根节点其下元素为 rank = nodeTreeRank(e)  rank少的 挂在 rank多的 节点上
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        // 如果 同根 就没必要 做 相关操作。
        if (pRoot == qRoot){
            return;
        }

        if (nodeTreeRank[pRoot] < nodeTreeRank[qRoot]){
            // p 指向 q
            parent[pRoot] = qRoot; // rank没有发生改变
        }else if (nodeTreeRank[qRoot] < nodeTreeRank[pRoot]){
            parent[qRoot] = pRoot; // rank没有发生改变
        }else {
            parent[qRoot] = pRoot;
            // rank 要加 1
            nodeTreeRank[pRoot] += 1;
        }
    }

    /**
     * 查找过程，查找元素p所对应的集合编号
     * O(h)复杂度，h为树的高度
     * @param p
     * @return
     */
    private int find(int p){
        if (p < 0 || p >= parent.length){
            throw new IllegalArgumentException("越界");
        }

        // 如果节点 指向了 自己 就说明找到了 集合 的 分组 标号
        while(p != parent[p]){
            p = parent[p];
        }

        return p;
    }
}
