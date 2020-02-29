package org.chiefdata.tree.node;

/**
 * @author : Kevin
 * @Title : Node
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/29 11:42
 * @Modifyed By :
 */
public class Node<E> {
    /**
     * 数据域
     */
    public E data;
    /**
     * 左右子树
     */
    public Node<E> left, right;

    public Node(E e){
        this.data = e;
        this.left = this.right = null;
    }
}
