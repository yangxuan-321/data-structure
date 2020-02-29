package org.chiefdata.treeold.binart_sort_tree;

/**
 * @author : Kevin
 * @Title : TreeNode
 * @ProjectName data-structure
 * @Description : 二叉树的基本节点单位
 * @Time : Created in 2019/5/23 14:05
 * @Modifyed By :
 */
public class TreeNode<T> {
    //数据域
    public T data;
    //左孩子 -- 引用
    public TreeNode<T> left;
    //右孩子 -- 引用
    public TreeNode<T> right;
    //如果多个
    public TreeNode<T> head;

    public TreeNode() {
    }

    public TreeNode(T data) {
        this.data = data;
    }
}
