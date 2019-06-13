package org.chiefdata.tree.binart_avl_tree.node;

/**
 * @author Kevin
 * @Title: aaa
 * @ProjectName data-structure
 * @Description: TODO
 * @date 2019/6/12 17:55
 */
public class Entry<K, V>{
    public K key;
    private int hashCode;
    public V value;

    public Entry(K key, V value){
        this.key = key;
        this.value = value;
        if(null == this.key) {
            this.hashCode = 0;
        }

        //这里有问题以后改进
        this.hashCode = key.hashCode();
    }

    public int getHashCode() {
        return hashCode;
    }
}