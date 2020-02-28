package org.chiefdata.treeold.binart_avl_tree.node;

/**
 * @author Kevin
 * @Title: AVLNode
 * @ProjectName data-structure
 * @Description: TODO
 * @date 2019/6/12 17:52
 */
public class AVLNode<K, V> {
    public Entry<K, V> data;
    public AVLNode<K, V> left;
    public AVLNode<K, V> right;
    //高度
    public int height;

    public AVLNode(K key, V value){
        this.data = new Entry<K, V>(key, value);
        this.height = 1;
    }
}
