package org.chiefdata.linkedlist;

/**
 * @author : Kevin
 * @Title : LinkListTesy
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/27 16:01
 * @Modifyed By :
 */
public class LinkListTest {
    public static void main(String[] args) {
        LinkedListNoneHead<Integer> list = new LinkedListNoneHead<Integer>();
        for (int i = 0; i < 10; i++) {
            list.addForHead(i);
        }

        System.out.println(list);

        System.out.println(list.get(9));

        list.add(2, 100);
        System.out.println(list);

        list.remove(2);
        System.out.println(list);
    }
}
