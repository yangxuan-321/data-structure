package org.chiefdata.tree.unionfind;

/**
 * @author : Kevin
 * @Title : UnionFind
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/6 18:20
 * @Modifyed By :
 */
public interface UnionFind {
    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
