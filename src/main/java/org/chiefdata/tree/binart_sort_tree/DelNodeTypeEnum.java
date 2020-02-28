package org.chiefdata.tree.binart_sort_tree;

/**
 * @author : Kevin
 * @Title : DelNodeTypeEnum
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2019/6/12 23:14
 * @Modifyed By :
 */
public enum DelNodeTypeEnum {
    UNKNOW,                 //未知
    DEL_LEAF_NODE,          //删除的节点为叶子节点
    DEL_ONLY_L_NODE,        //删除的节点只有左子树
    DEL_ONLY_R_NODE,        //删除的节点只有右子树
    DEL_L_AND_R_NODE;       //删除的节点既有左子树也有右子树
}
