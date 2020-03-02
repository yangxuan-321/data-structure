package org.chiefdata.map;


import java.text.MessageFormat;
import java.util.Comparator;

/**
 * @author : Kevin
 * @Title : LinkedListMap
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/1 15:07
 * @Modifyed By :
 */
public class LinkedListMap<K, V> implements Map<K, V> {
    private KVNode<K, V> dummyHead;
    private int size;

    private Comparator<K> comparator;

    public LinkedListMap(){
        this.dummyHead = new KVNode<K, V>();
        this.size = 0;
        this.comparator = (e1, e2)->{
            if (e1 instanceof Comparable){
                return ((Comparable) e1).compareTo(e2);
            }else {
                return e1.hashCode() - e2.hashCode();
            }
        };
    }

    public LinkedListMap(Comparator<K> comparator){
        this.dummyHead = new KVNode<K, V>();
        this.size = 0;
        this.comparator = comparator;
    }

    @Override
    public void add(K key, V value) {
        KVNode<K, V> node = getNode(key);
        if (node != null){
            // 做替换
            node.value = value;
            return;
        }
        size++;
        dummyHead.next = add(dummyHead.next, key, value); //这种方法 如果 元素太多 容易 栈溢出
    }

    private KVNode<K, V> add(KVNode<K, V> node, K key, V value) {
        if (node == null){
            return new KVNode<K, V>(key, value);
        }

        node.next = add(node.next, key, value);
        return node;
    }

    @Override
    public V remove(K key) {
        KVNode<K, V> node = dummyHead;
        while (node.next != null){
            if (0 == comparator.compare(node.next.key, key)){
                KVNode<K, V> old = node.next;
                node.next = old.next;
                old.next = null;
                return old.value;
            }else {
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return null != getNode(key);
    }

    @Override
    public V get(K key) {
        KVNode<K, V> node = getNode(key);
        return null == node ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        KVNode<K, V> node = getNode(key);
        if (null == node){
            throw new RuntimeException("key不存在");
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

    private KVNode<K, V> getNode(K key){
        return getNode(dummyHead.next, key);
    }

    private KVNode<K, V> getNode(KVNode<K, V> node, K key){
        if (node == null){
            return null;
        }

        int compare = comparator.compare(node.key, key);
        if (compare == 0){
            return node;
        }else {
            return getNode(node.next, key);
        }
    }

    @Override
    public String toString() {
        KVNode<K, V> node = dummyHead.next;
        String str = "";
        while (node != null){
            str += node.toString() + "->";
            node = node.next;
        }
        return str;
    }

    private class KVNode<K, V>{
        public K key;
        public V value;
        public KVNode<K, V> next;
        public KVNode(){
            this.key = null;
            this.value = null;
        }

        public KVNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            String tmplete = "[key:{0}, value:{1}]";

            return MessageFormat.format(tmplete, String.valueOf(key), String.valueOf(value));
        }
    }
}
