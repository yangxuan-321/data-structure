package org.chiefdata.tree.binart_avl_tree;

import org.chiefdata.tree.binart_avl_tree.enums.RotateTypeEnum;
import org.chiefdata.tree.binart_avl_tree.node.AVLNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin
 * @Title: AVLTree
 * @ProjectName data-structure
 * @Description: TODO
 * @date 2019/6/13 14:31
 */
public class AVLTree<K, V> {
    //根节点
    private AVLNode<K, V> root;

    //无参构造
    public AVLTree() {

    }

    //参数 为 另一棵树
    public AVLTree(AVLTree<K, V> tree) {

    }

    // put 一个 进来
    public void put(K key, V value){
        if (null == value || null == key){
            throw new RuntimeException("key or value is can not be null...");
        }

        if (null == root){
            root = new AVLNode<K, V>(key, value);
        }

        Map<String, Object> resultMap = searchNodeByKey(key);
        if (true == (Boolean)resultMap.get("isFind")){
            //找到了 - 不做插入
            System.out.println("已经存在元素了,不用做插入");
        }

        AVLNode<K, V> pNode = (AVLNode<K, V>)resultMap.get("parentNode");
        int pHashCode = pNode.data.hashCode();
        int keyHashCode = key.hashCode();


        //四种类型的插入
        switch (RotateTypeEnum.LL){
            case LL:
                break;
        }
    }

    public V get(K key){
        return (V)(searchNodeByKey(key).get("currNode"));
    }

    private Map<String, Object> searchNodeByKey(K key){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("currNode", root);
        map.put("isFind", false);
        return searchNodeByKey(root.data.key, map);
    }

    /**
     * Map 包含 key 为 父节点 和 当前节点。 以及查找结果
     *      parentNode, currNode, isFind
     * @param key
     * @param map
     * @return
     */
    private Map<String, Object> searchNodeByKey(K key, Map<String, Object> map){
        AVLNode<K, V> currNode = (AVLNode<K, V>)map.get("currNode");
        boolean isFind = (Boolean)map.get("isFind");

        if(null == currNode || isFind){
            return map;
        }

        int nodeDataCode = currNode.data.key.hashCode();
        int dataCode = key.hashCode();

        //采用 前序-遍历  根 左 右
        if(dataCode == nodeDataCode){
            map.put("isFind", true);
            return map;
        }

        //左边的 都比 他小
        if(dataCode < nodeDataCode){
            map.put("currNode", currNode.left);
            map.put("parentNode", currNode);
            return searchNodeByKey(key, map);
        }

        //右边的 都比 他大
        map.put("currNode", currNode.right);
        map.put("parentNode", currNode);
        return searchNodeByKey(key, map);
    }


    //https://www.cnblogs.com/zhuwbox/p/3636783.html
    //https://blog.csdn.net/liushengxi_root/article/details/81660375
    //判断插入类型
    public RotateTypeEnum rotateType(){
        return RotateTypeEnum.LL;
    }
}
