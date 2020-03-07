package org.chiefdata.tree.avl;

import org.chiefdata.map.Map;
import org.chiefdata.tree.node.KVNode;

import java.util.*;

/**
 * @author : Kevin
 * @Title : BSTMap
 * @ProjectName data-structure
 * @Description : AVL平衡树 是 对 二分搜索树的 改进。
 * @Time : Created in 2020/2/29 14:24
 * @Modifyed By :
 */
public class AVLTree<K, V>{
    private Comparator<K> comparator;

    private KVNode<K, V> root;
    private int size;

    public AVLTree(){
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

    public AVLTree(Comparator<K> comparator){
        this.root = null;
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * 计算某个节点的平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(KVNode<K, V> node){
        if (node == null){
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(KVNode<K, V> node){
        if (node == null){
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        // 判断此节点 不是 平衡的 就直接返回false
        if (Math.abs(balanceFactor) > 1){
            return false;
        }

        // 否则 就去 寻找 左节点和右节点
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 判断某个树是否为 二分搜索树
     * @return
     */
    public boolean isBST(){
        List<K> keys = new ArrayList<K>();
        middleOrder(root, keys);
        for (int i = 0; i < keys.size() - 1; i++) {
            if (comparator.compare(keys.get(i), keys.get(i + 1)) > 0){
                return false;
            }
        }

        return true;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 递归切记，上一层的调用，归上一层管。当前 只看 当前
     * @param node
     * @param key
     * @param value
     * @return
     */
    public KVNode<K, V> add(KVNode<K, V> node, K key, V value){
        // 递归到底
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

        // 对当前 node 的height 值 进行更新
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 判断是否平衡
        int balanceFactor = getBalanceFactor(node);
        /**
            if (Math.abs(balanceFactor) > 1){
                System.out.println("该节点出现了不平衡, 平衡因子为:" + balanceFactor);
            }
        */

        // 维护平衡性
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            // 1. 太 偏左了 就右旋转
            // 1.1 node 的左边重 且 1.2 node.left的左边重(或者相等)
            // 即 if 想表达的是：首先树是不平衡的向左倾斜，且新插的节点是 不平衡 的 左侧

            // 旋转完的 节点 接上去
            return rightRotate(node);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            // 1. 太 偏右了 就左旋转
            // 1.1 node 的右边重 且 1.2 node.right的右边重(或者相等)
            // 即 if 想表达的是：首先树是不平衡的向右倾斜，且新插的节点是 不平衡 的 右侧

            return leftRotate(node);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            // 1.1 node 的左边重 且 1.2 node.left的右边重
            // 即 if 想表达的是：首先树是不平衡的向左倾斜，且新插的节点是 不平衡 的 右侧
            // 维护平衡性 分两步：一、左旋转(将LR -> RL) 二、右旋转
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            // 1.1 node 的右边重 且 1.2 node.right的左边重
            // 即 if 想表达的是：首先树是不平衡的向左倾斜，且新插的节点是 不平衡 的 右侧
            // 维护平衡性 分两步：一、右旋转(将RL -> RR) 二、左旋转
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // 将维护完平衡性 的节点 返回给 上一层调用
        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2

    // LL 型 -> 右旋转
    private KVNode<K, V> rightRotate(KVNode<K, V> y){
        // x.right = y;
        KVNode<K, V> x = y.left;
        KVNode<K, V> T3 = x.right;

        // 向右旋转
        x.right = y;
        y.left = T3;

        // 更新节点 height 值。只有 x 和 y 的节点高度值 发生变化
        // 先更新 y 的值。因为 x的height值。与y相关
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4

    // RR 型 -> 左旋转
    private KVNode<K, V> leftRotate(KVNode<K, V> y){
        KVNode<K, V> x = y.right;
        KVNode<K, V> T2 = x.left;

        // 右旋
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

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

    public boolean contains(K key) {
        return null != get(key);
    }

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

    public void set(K key, V newValue) {
        KVNode<K, V> node = getNode(root, key);
        if (null == node){
            throw new RuntimeException("找不到元素");
        }

        node.value = newValue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return 0 == size;
    }

    /**
     * 获得节点的高度
     * @param node
     * @return
     */
    public int getHeight(KVNode<K, V> node){
        if (null == node){
            return 0;
        }

        return node.height;
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
     * 中序遍历
     * @param node
     * @param keys
     */
    private void middleOrder(KVNode<K, V> node, List<K> keys){
        if (node == null){
            return;
        }

        middleOrder(node.left, keys);
        keys.add(node.key);
        middleOrder(node.right, keys);
    }

}
