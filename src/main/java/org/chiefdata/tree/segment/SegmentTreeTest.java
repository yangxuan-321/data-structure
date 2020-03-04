package org.chiefdata.tree.segment;

/**
 * @author : Kevin
 * @Title : SegmentTreeTest
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/4 17:03
 * @Modifyed By :
 */
public class SegmentTreeTest {
    public static void main(String[] args) {
        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(new Integer[]{-2, 0, 3, -5, 2, -1}, (e1, e2)->{
            return e1 + e2;
        });

//        System.out.println(segmentTree);
        Integer sum = segmentTree.query(0, 2);
        System.out.println(sum);
        sum = segmentTree.query(3, 5);
        System.out.println(sum);
        sum = segmentTree.query(0, 5);
        System.out.println(sum);
    }
}
