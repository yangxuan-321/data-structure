package org.chiefdata.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Kevin
 * @Title : Q203
 * @ProjectName data-structure
 * @Description : 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * @Time : Created in 2020/2/28 11:30
 * @Modifyed By :
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x;}
}

public class Q203 {
    /**
     * 不使用虚拟头结点
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements1(ListNode head, int val) {
        while(head != null && head.val == val){
            ListNode old = head;
            head = head.next;
            old.next = null;
        }

        if(head == null){
            return head;
        }

        // 此时的head就一定 不需要删除了
        ListNode prev = head;

        while(prev.next != null){
            if(prev.next.val == val){
                ListNode old = prev.next;
                prev.next = old.next;
                old.next = null;
            }else{
                prev = prev.next;
            }
        }
        return head;
    }

    /**
     * 使用虚拟头结点
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;

        while(prev.next != null){
            if(prev.next.val == val){
                ListNode old = prev.next;
                prev.next = old.next;
                old.next = null;
            }else{
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 使用递归
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements3(ListNode head, int val) {

//        ListNode e = removeElements3(head.next, val);
//        if (head.val == val){
//            return e;
//        }else {
//            head.next = e;
//            return head;
//        }

        //1
        if (head == null){
            return null;
        }

        //2
        head.next = removeElements3(head.next, val);

        //3
        return head.val == val ? head.next : head;
    }

    /**
     * depth 代表 递归深度
     * @param head
     * @param val
     * @param depth
     * @return
     */
    public static ListNode removeElements3Print(ListNode head, int val, int depth) {
        String depthString = getGenerateDepthString(depth);

        System.out.print(depthString);
        System.out.println("call: remove " + val + " in " + displayList(head));

        //1
        if (head == null){
            System.out.print(depthString);
            System.out.print("Return: " + displayList(head));
            return null;
        }

        //2
        ListNode res = removeElements3Print(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + displayList(res));

        ListNode ret;
        if (head.val == val){
            ret = res;
        }else {
            head.next = res;
            ret = head;
        }

        System.out.print(depthString);
        System.out.println("Return: " + displayList(ret));
        //3
        return ret;
    }

    private static String getGenerateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }

        return res.toString();
    }

    public static void main(String[] args) {
        Q203 q203 = new Q203();
        ListNode head = q203.buildList();
        head = q203.removeElements3Print(head, 6, 0);
        System.out.println(displayList(head));
    }

    public ListNode buildList(){
        List<Integer> list = Arrays.asList(1, 2, 6, 3, 4, 5, 6);
        ListNode head = new ListNode(list.get(0));
        ListNode tmp = head;
        for (Integer i: list) {
            if (i == 0){
                continue;
            }

            tmp.next = new ListNode(list.get(i));
            tmp = tmp.next;
        }

        return head;
    }

    public static String displayList(ListNode head){
        String str = "";
        while (head != null){
            str = str + head.val + "->";
            head = head.next;
        }
        return str + "NULL";
    }
}
