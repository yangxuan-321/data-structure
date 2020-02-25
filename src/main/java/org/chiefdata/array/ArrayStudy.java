package org.chiefdata.array;

/**
 * @author : Kevin
 * @Title : ArrayStudy
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/25 14:36
 * @Modifyed By :
 */
public class ArrayStudy {
    // 数组最大的优点：快速查询
    // 数组最好运用于"索引有语义的情况"
    // 但并非所有有语义的索引都适用于数组
    public static void main(String[] args) {
        Array<Integer> array = new Array<Integer>();
        for (int i = 0; i < 10; i++) {
            array.add(i);
        }

        array.remove(9);

        System.out.println(array);

        Integer integer = array.find((i) -> i.intValue() == 3);
        System.out.println(integer);
    }
}
