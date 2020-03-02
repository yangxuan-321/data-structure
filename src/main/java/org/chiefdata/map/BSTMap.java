package org.chiefdata.map;

import org.chiefdata.tree.node.KVNode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author : Kevin
 * @Title : BSTMap
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/29 14:24
 * @Modifyed By :
 */
public class BSTMap<K, V> implements Map<K, V> {
    private Comparator<K> comparator;

    private KVNode<K, V> root;
    private int size;

    public BSTMap(){
        this.root = null;
        this.size = 0;
        this.comparator = this.comparator = (e1, e2)->{
            if (e1 instanceof Comparable){
                return ((Comparable) e1).compareTo(e2);
            }else {
                return e1.hashCode() - e2.hashCode();
            }
        };
    }

    public BSTMap(Comparator<K> comparator){
        this.root = null;
        this.size = 0;
        this.comparator = comparator;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    public KVNode<K, V> add(KVNode<K, V> node, K key, V value){
        if (node == null){
            size++;
            return new KVNode<K, V>(key, value);
        }

        int compare = comparator.compare(node.key, key);
        if (compare == 0){
            // do none thing
            node.value = value;
        }else if (compare < 0){ //去左子树添加
            node.right = add(node.right, key, value);
        }else {                 //去右子树添加
            node.left = add(node.left, key, value);
        }

        return node;
    }

    @Override
    public V remove(K key) {
        KVNode<K, V> node = getNode(root, key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }

        return null;
    }

    private KVNode<K, V> remove(KVNode<K, V> node, K key){
        if (node == null){
            return null;
        }

        int compare = comparator.compare(node.key, key);
        if (0 == compare){
            // 需要被删除
            // 待删除节点左子树为空的情况
            if(node.left == null){
                KVNode rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                KVNode leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            KVNode successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;

        }else if (compare < 0){
            node.right = remove(node.right, key);
            return node;
        }else {
            node.left = remove(node.left, key);
            return node;
        }
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private KVNode<K, V> minimum(KVNode<K, V> node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private KVNode<K, V> removeMin(KVNode<K, V> node){

        if(node.left == null){
            KVNode<K, V> rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return null != get(key);
    }

    @Override
    public V get(K key) {
        KVNode<K, V> node = getNode(root, key);
        return null == node ? null : node.value;
    }

    private KVNode<K, V> getNode(KVNode<K, V> node, K key){
        if (node == null){
            return null;
        }

        int compare = comparator.compare(node.key, key);
        if (compare == 0){
            return node;
        }else if (compare < 0){
            return getNode(node.right, key);
        }else {
            return getNode(node.left, key);
        }
    }

    @Override
    public void set(K key, V newValue) {
        KVNode<K, V> node = getNode(root, key);
        if (null == node){
            throw new RuntimeException("找不到元素");
        }

        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    /**
     * 前序
     */
    public void preQuery(){
        preQuery(root);
        System.out.println();
    }

    private void preQuery(KVNode<K,V> node) {
        if (null == node){
            return;
        }

        // 前序遍历 就代表 访问节点在前
        System.out.print(node.value + "->");
        preQuery(node.left);
        preQuery(node.right);
    }

    /**
     * 中序
     */
    public void middleQuery(){
        middleQuery(root);
        System.out.println();
    }

    private void middleQuery(KVNode<K, V> node) {
        if (null == node){
            return;
        }

        middleQuery(node.left);
        System.out.print(node.value + "->");
        middleQuery(node.right);
    }

    /**
     * 后序
     */
    public void afterQuery(){
        afterQuery(root);
        System.out.println();
    }

    private void afterQuery(KVNode<K, V> node) {
        if (null == node){
            return;
        }

        afterQuery(node.left);
        afterQuery(node.right);
        System.out.print(node.value + "->");
    }

    /**
     * 前序遍历无递归写法
     * 需要使用数据结构 - 栈
     */
    public void preQueryNoneDigui(){
        Stack<KVNode<K, V>> stack = new Stack<KVNode<K, V>>();
        stack.push(root);
        while (!stack.isEmpty()){
            KVNode<K, V> pop = stack.pop();

            System.out.print(pop.value + "->");

            if (pop.right != null){
                stack.push(pop.right);
            }
            if (pop.left != null){
                stack.push(pop.left);
            }
        }
        System.out.println();
    }

    /**
     * 层序遍历
     */
    public void leverQuery(){
        Queue<KVNode<K, V>> queue = new LinkedList<KVNode<K, V>>();
        queue.add(root);
        while (!queue.isEmpty()){
            KVNode<K, V> curr = queue.poll();
            System.out.print(curr.value + "->");
            if (curr.left != null){
                queue.add(curr.left);
            }
            if (curr.right != null){
                queue.add(curr.right);
            }
        }

        System.out.println();
    }
}
