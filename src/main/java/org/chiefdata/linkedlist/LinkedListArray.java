package org.chiefdata.linkedlist;

/**
 * @author : Kevin
 * @Title : LinkedListArray
 * @ProjectName data-structure
 * @Description : 数组 链表
 * @Time : Created in 2020/2/28 18:47
 * @Modifyed By :
 */
public class LinkedListArray<E> {



    private class Node<E>{
        // 数据域
        public E data;

        // 指针域(指向下一个节点)
        public int next;

        public Node(){

        }

        public Node(E e){
            this.data = e;
        }

        public Node(E e, int next){
            this.data = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return data + "";
        }
    }
}
