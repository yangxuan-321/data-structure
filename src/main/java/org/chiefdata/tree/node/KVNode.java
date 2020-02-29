package org.chiefdata.tree.node;

/**
 * @author : Kevin
 * @Title : KVNode
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/29 14:39
 * @Modifyed By :
 */
public class KVNode<K, V> {
    public K key;
    public V value;
    public KVNode<K, V> left, right;

    public KVNode(K key, V value){
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
