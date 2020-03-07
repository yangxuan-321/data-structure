package org.chiefdata.tree.unionfind;

/**
 * @author : Kevin
 * @Title : UnionFileImpl1
 * @ProjectName data-structure
 * @Description : 数组 模拟 并查集
 * @Time : Created in 2020/3/6 18:21
 * @Modifyed By :
 */
public class UnionFind1 implements UnionFind {
    /**
     * 存放每一个数据所对应的所属的那个集合编号
     */
    private int[] id;

    public UnionFind1(int size){
        id = new int[size];

        for (int i = 0; i < id.length; i++) {
            // 初始化 使 每一个元素所对应的的 集合 编号 都不一样
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 时间复杂度 O(1)
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 时间复杂度 O(n)
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (pid == qid){
            return;
        }

        // 合并
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid){
                id[i] = qid;
            }
        }
    }

    /**
     * 查找元素p所对应的集合编号
     * 每一个集合都有一个编号 一个集合里有多个元素
     * id数组存储的是每个元素的集合编号
     * @param p
     * @return
     */
    private int find(int p){
        if (p < 0 || p > id.length){
            throw new IllegalArgumentException("越界");
        }

        return id[p];
    }
}
