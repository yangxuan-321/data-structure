package org.chiefdata.linkedlist;

/**
 * @author : Kevin
 * @Title : LinkedList2Dir
 * @ProjectName data-structure
 * @Description : 双向链表
 * @Time : Created in 2020/2/28 18:43
 * @Modifyed By :
 */
public class LinkedList2Dir<E> {


    private Node<E> head, tail;


    private class Node<E>{
        // 数据域
        public E data;

        // 指针域(指向下一个节点)
        public Node<E> prev, next;

        public Node(){

        }

        public Node(E e){
            this.data = e;
        }

        public Node(E e, Node<E> next){
            this.data = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return data + "";
        }
    }
}
