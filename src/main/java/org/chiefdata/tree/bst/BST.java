package org.chiefdata.tree.bst;

import org.checkerframework.checker.units.qual.K;
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
        this.comparator = this.comparator = (e1, e2)->{
            if (e1 instanceof Comparable){
                return ((Comparable) e1).compareTo(e2);
            }else {
                return e1.hashCode() - e2.hashCode();
            }
        };
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

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node<E> remove(Node<E> node, E e) {
        if (node == null){
            return null;
        }

        int compare = comparator.compare(node.data, e);
        if (0 == compare){
            // 找到该节点了
            if (node.left == null){//如果左子树为空,让该节点的右孩子来顶替
                Node<E> rightNode = node.right;
                node.right = null;  // 这句话 要不要 都行。
                size--;
                return rightNode;
            }

            if (node.right == null){//如果右子树为空,让该节点的左孩子来顶替
                Node<E> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

//            if (node.left != null && node.right != null){//左右子树皆不为空,找到右孩子中最小的节点来顶替
                // 找到顶替节点
            Node<E> dingti = minimum(node.right);   //找到右孩子中最小值
            dingti.right = removeMin(node.right);   //删除右孩子的最小值,这里面会做size--动作
//                dingti.right = node.right;
            dingti.left = node.left;

            node.left = node.right = null; //可以不要这一句

            return dingti;
//            }


        }else if (compare < 0){
            node.right = remove(node.right, e);
            return node;
        }else {
            node.left = remove(node.left, e);
            return node;
        }
    }

    private Node<E> removeMin(Node<E> node) {
        if(node.left == null){
            Node<E> rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    private Node<E> minimum(Node<E> node) {
        if (node.left == null){
            return node;
        }

        return minimum(node.left);
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
