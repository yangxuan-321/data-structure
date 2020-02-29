package org.chiefdata.tree.bst;

import org.chiefdata.tree.node.Node;

import java.util.Comparator;

/**
 * @author : Kevin
 * @Title : BST
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/29 11:26
 * @Modifyed By :
 */
public class BST<E> {
    // 根结点
    private Node<E> root;
    // size
    private int size;
    // 比较规则
    private Comparator<E> comparator;

    public BST(){
        root = null;
        size = 0;
        // 默认使用hashCode作比较
        this.comparator = (e1, e2)->e1.hashCode()-e2.hashCode();
    }

    /**
     * 有参构造 需要 传入一个 比较器。用于比较 两个元素 大小
     * @param comparator
     */
    public BST(Comparator<E> comparator){
        root = null;
        size = 0;
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e) {
        root = add(root ,e);
    }

    /**
     * 向以node为根的二分搜索树中插入元素e，递归算法
     * 返回 插入新节点后二分搜索树的根
     * @param node
     * @param e
     * @return
     */
    private Node<E> add(Node<E> node, E e){

        if (node == null){
            size ++;
            return new Node<E>(e);
        }
        int compare = this.comparator.compare(node.data, e);
        if (compare == 0){
            // do none thing
        }else if (compare < 0){
            node.right = add(node.right, e);
        }else {
            node.left = add(node.left, e);
        }

        return node;
    }

    public E remove(E e) {
        return null;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 了解语义
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node<E> node, E e){
        if (node == null){
            return false;
        }

        int compare = this.comparator.compare(node.data, e);
        if (compare == 0){
            return true;
        }else if (compare < 0){
            return contains(node.right, e);
        }else{
            return contains(node.left, e);
        }
    }
}
