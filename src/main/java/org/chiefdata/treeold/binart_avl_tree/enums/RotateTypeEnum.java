package org.chiefdata.treeold.binart_avl_tree.enums;

/**
 * @author Kevin
 * @Title: RotateTypeEnum
 * @ProjectName data-structure
 * @Description: TODO
 * @date 2019/6/13 16:13
 */
public enum RotateTypeEnum {
    UNKNOW,         //未知
    LL,             //左左情况，右旋转
    LR,             //左右情况，双旋转,先左后右
    RR,             //右右情况，左旋转
    RL;             //右左情况，双旋转,先右后左
}
