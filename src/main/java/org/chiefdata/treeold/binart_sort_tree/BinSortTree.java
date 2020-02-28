package org.chiefdata.treeold.binart_sort_tree;

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
    public int insert(T data){
        if (null == data){
            return 0;
        }

        if (null == root){
            root = new TreeNode<T>(data);
            return 1;
        }

        Map<String, Object> resultMap = searchNode(data);
        if (true == (Boolean)resultMap.get("isFind")){
            //找到了 - 不做插入
            System.out.println("已经存在元素了,不用做插入");
        }

        TreeNode<T> pNode = (TreeNode<T>)resultMap.get("parentNode");
        int pHashCode = pNode.data.hashCode();
        int nodeHashCode = data.hashCode();
        TreeNode<T> node = new TreeNode<T>(data);
        if(pHashCode > nodeHashCode){
            pNode.left = node;
        }else {
            pNode.right = node;
        }

        return 1;
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
        sortDisplay(root, ts);
        return ts;
    }

    private void sortDisplay(TreeNode<T> node, List<T> ts){
        if (null == node){
            return;
        }

        if(null != node.left){
            sortDisplay(node.left, ts);
        }

        ts.add(node.data);

        if(null != node.right){
            sortDisplay(node.right, ts);
        }

    }

    /**
     * 二叉排序树的删除操作
     * @param data
     * @return
     */
    public TreeNode<T> delete(T data){
        Map<String, Object> resultMap = searchNode(data);
        if (false == (Boolean)resultMap.get("isFind")){
            //不存在该元素 - 无需做删除
            System.out.println("不存在该元素, 无需做删除");
        }

        TreeNode<T> parentNode = (TreeNode<T>)resultMap.get("parentNode");
        TreeNode<T> currNode = (TreeNode<T>)resultMap.get("currNode");

        switch (deleteType(currNode)){
            //删除的节点为叶子节点
            case DEL_LEAF_NODE:
                deleteLeafNode(parentNode, sonNodeType(parentNode, currNode));
                break;

            //删除的节点只有左子树
            case DEL_ONLY_L_NODE:
                deleteOnlyLeftNode(parentNode, currNode, sonNodeType(parentNode, currNode));
                break;

            //删除的节点只有右子树
            case DEL_ONLY_R_NODE:
                deleteOnlyRightNode(parentNode, currNode, sonNodeType(parentNode, currNode));
                break;

            //删除的节点既有左子树也有右子树
            case DEL_L_AND_R_NODE:
                delLeftAndRightNode(parentNode, currNode, sonNodeType(parentNode, currNode));
                break;

            default:
                break;
        }

        return null;
    }

    //判断符合哪种删除方式
    private DelNodeTypeEnum deleteType(TreeNode<T> currNode){
        if (null == currNode.left && null == currNode.right){
            return DelNodeTypeEnum.DEL_LEAF_NODE;
        }

        if (null == currNode.right){
            return DelNodeTypeEnum.DEL_ONLY_L_NODE;
        }

        if (null == currNode.left){
            return DelNodeTypeEnum.DEL_ONLY_R_NODE;
        }

        return DelNodeTypeEnum.DEL_L_AND_R_NODE;
    }

    /**
     * 删除 的老哥 是 叶子节点的
     * @param parentNode
     * @param sonNodeType
     */
    private void deleteLeafNode(TreeNode<T> parentNode, SonNodeTypeEnum sonNodeType){
        switch (sonNodeType){
            case LEFT_SON:
                parentNode.left = null;
                break;
            case RIGHT_SON:
                parentNode.right = null;
                break;
            case ROOT:
                this.root = null;
                break;
            default:
                break;
        }
    }

    /**
     * 删除的 老哥 仅有左儿子
     * @param parentNode
     * @param currNode
     * @param sonNodeType
     */
    private void deleteOnlyLeftNode(TreeNode<T> parentNode, TreeNode<T> currNode, SonNodeTypeEnum sonNodeType){
        switch (sonNodeType){
            case LEFT_SON:
                parentNode.left = currNode.left;
                break;
            case RIGHT_SON:
                parentNode.right = currNode.left;
                break;
            case ROOT:
                root = currNode.left;
                break;
            default:
                break;
        }
    }

    /**
     * 删除的 老哥 仅有右儿子
     * @param parentNode
     * @param currNode
     * @param sonNodeType
     */
    private void deleteOnlyRightNode(TreeNode<T> parentNode, TreeNode<T> currNode, SonNodeTypeEnum sonNodeType){
        switch (sonNodeType){
            case LEFT_SON:
                parentNode.left = currNode.right;
                break;
            case RIGHT_SON:
                parentNode.right = currNode.right;
                break;
            case ROOT:
                root = currNode.right;
                break;
            default:
                break;
        }
    }

    /**
     * 删除 左右儿子 都有 的老哥
     * @param parentNode
     * @param currNode
     * @param sonNodeType
     */
    private void delLeftAndRightNode(TreeNode<T> parentNode, TreeNode<T> currNode, SonNodeTypeEnum sonNodeType){
        //拿当前节点最右的左子树
//        Map<String, Object> resultMap = findMostLeftNode_(currNode);
        Map<String, Object> resultMap = findNodeRightSonMonstLeftNode(currNode);
        TreeNode<T> mostLeftNode = (TreeNode<T>) resultMap.get("mostLeftNode");
        TreeNode<T> mostLeftNodeParent = (TreeNode<T>) resultMap.get("parentNode");
        if (!(sonNodeType == SonNodeTypeEnum.LEFT_SON || sonNodeType == SonNodeTypeEnum.RIGHT_SON || sonNodeType == SonNodeTypeEnum.ROOT)){
            return;
        }

        switch (sonNodeType(mostLeftNodeParent, mostLeftNode)){
            case LEFT_SON:
                mostLeftNodeParent.left = null;
                break;
            case RIGHT_SON:
                mostLeftNodeParent.right = null;
                break;
            default:
                break;
        }

        mostLeftNode.left = currNode.left;
        mostLeftNode.right = currNode.right;

        switch (sonNodeType){
            case LEFT_SON:
                parentNode.left = mostLeftNode;
                break;
            case RIGHT_SON:
                parentNode.right = mostLeftNode;
                break;
            case ROOT:
                root = mostLeftNode;
                break;
            default:
                break;
        }
    }

    /**
     * 给出 此节点 儿子节点 是 属于父节点 的 左儿子 还是 右儿子
     * @param parentNode
     * @param currNode
     * @return
     */
    private SonNodeTypeEnum sonNodeType(TreeNode<T> parentNode, TreeNode<T> currNode){

        if (null == parentNode){
            return SonNodeTypeEnum.ROOT;
        }

        int hashCode = currNode.hashCode();
        if (null != parentNode.left && parentNode.left.hashCode() == hashCode){
            System.out.println("left");
            return SonNodeTypeEnum.LEFT_SON;
        }

        if (null != parentNode.right && parentNode.right.hashCode() == hashCode){
            System.out.println("right");
            return SonNodeTypeEnum.RIGHT_SON;
        }

        return SonNodeTypeEnum.UNKNOW;
    }

    /**
     * 寻找 最左 的左子树
     * @param node
     * @return
     */
    private TreeNode<T> findMostLeftNode(TreeNode<T> node){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentNode", node);
        map.put("currNode", node);
        return findMostLeftNode(node, map);
    }

    private Map<String, Object> findNodeRightSonMonstLeftNode(TreeNode<T> node){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentNode", node);
        map.put("mostLeftNode", node.right);
        findMostLeftNode(node.right, map);
        return map;
    }

    private Map<String, Object> findMostLeftNode_(TreeNode<T> node){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentNode", node);
        map.put("mostLeftNode", node);
        findMostLeftNode(node, map);
        return map;
    }

    private TreeNode<T> findMostLeftNode(TreeNode<T> node, Map<String, Object> map){
        if (null == node || null == node.left){
            return node;
        }

        map.put("parentNode", node);
        map.put("mostLeftNode", node.left);

        return findMostLeftNode(node.left, map);
    }

    public TreeNode<T> testFindMostLeftNode(){
        return findMostLeftNode(root);
    }

}
