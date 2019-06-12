package org.chiefdata.tree.binart_sort_tree;

import org.chiefdata.util.CollectionUtils;

import java.util.*;

/**
 * @author : Kevin
 * @Title : BinSortTree
 * @ProjectName data-structure
 * @Description : 二叉排序树
 * @Time : Created in 2019/5/23 14:10
 * @Modifyed By :
 */
public class BinSortTree<T> {
    //排序二叉树的根节点
    private TreeNode<T> root;

    //无参构造
    public BinSortTree() {

    }

    //可以传递 -- 集合类型的参数
    public BinSortTree(List<T> lists){

    }

    //可以传递 -- 数组类型
    public BinSortTree(T[] arrs){

    }

    //可以传递 多参数类型 (编译不通过 因为 T... args 会编译成 T[] args 所以存在 方法签名相同的情况)
    /**
    public BinSortTree(T... args){

    }
    */

    public TreeNode<T> search(T data){
        if (null == data){
            return null;
        }

        Map<String, Object> searchResult = searchNode(data);
        if(true == (Boolean)searchResult.get("isFind")){
            return (TreeNode<T>)searchResult.get("currNode");
        }

        return null;
    }



    private Map<String, Object> searchNode(T data){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("currNode", root);
        map.put("isFind", false);
        return searchNode(data, map);
    }

    /**
     * Map 包含 key 为 父节点 和 当前节点。 以及查找结果
     *      parentNode, currNode, isFind
     * @param data
     * @param map
     * @return
     */
    private Map<String, Object> searchNode(T data, Map<String, Object> map){
        TreeNode<T> currNode = (TreeNode<T>)map.get("currNode");
        boolean isFind = (Boolean)map.get("isFind");

        if(null == currNode || isFind){
            return map;
        }

        int nodeDataCode = currNode.data.hashCode();
        int dataCode = data.hashCode();

        //采用 前序-遍历  根 左 右
        if(dataCode == nodeDataCode){
            map.put("isFind", true);
            return map;
        }

        //左边的 都比 他小
        if(dataCode < nodeDataCode){
            map.put("currNode", currNode.left);
            map.put("parentNode", currNode);
            return searchNode(data, map);
        }

        //右边的 都比 他大
        map.put("currNode", currNode.right);
        map.put("parentNode", currNode);
        return searchNode(data, map);
    }



    /**
     * 对排序二叉树 进行插入
     * @return 插入成功的节点个数
     */
    public int insert(List<T> lists){
        if (CollectionUtils.isEmpty(lists)){
            return 0;
        }

        if (null == root){
            root = new TreeNode<T>(lists.get(0));
        }

        int successCnt = 0;
        for (int i = 1; i < lists.size(); i++){
            T data = lists.get(i);
            Map<String, Object> resultMap = searchNode(data);
            if (true == (Boolean)resultMap.get("isFind")){
                //找到了 - 不做插入
                System.out.println("已经存在元素了,不用做插入");
                continue;
            }

            TreeNode<T> parentNode = (TreeNode<T>)resultMap.get("parentNode");
            int pHashCode = parentNode.data.hashCode();
            int nodeHashCode = data.hashCode();
            if(pHashCode > nodeHashCode){
                parentNode.left = new TreeNode<T>(data);
            }else {
                parentNode.right = new TreeNode<T>(data);
            }

            successCnt ++;
        }

        return successCnt;
    }

    public int insert(T[] arrs){
        return insert(Arrays.asList(arrs));
    }

    public List<T> sortDisplay(){
        ArrayList<T> ts = new ArrayList<T>();
        sortisplay(root, ts);
        return ts;
    }

    private void sortisplay(TreeNode<T> node, List<T> ts){
        if (null == node){
            return;
        }

        if(null != node.left){
            sortisplay(node.left, ts);
        }

        ts.add(node.data);

        if(null != node.right){
            sortisplay(node.right, ts);
        }

    }

    /**
     * 二叉排序树的删除操作
     * @param data
     * @return
     */
    public TreeNode<T> delete(T data){
        return null;
    }

}
