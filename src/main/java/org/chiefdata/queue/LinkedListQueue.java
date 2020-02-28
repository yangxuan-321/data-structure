package org.chiefdata.queue;

import org.chiefdata.linkedlist.LinkedListNoneHead;

/**
 * @author : Kevin
 * @Title : LinkedListQueue
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/28 9:57
 * @Modifyed By :
 */
public class LinkedListQueue<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedListQueue(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(E e) {
        Node<E> node = new Node<>(e);
        if (tail == null){
            head = tail = node;
            return;
        }

        tail.next = node;
        tail = node;
        size++;
    }

    @Override
    public E poll() {
        if (head == null){
            throw new RuntimeException("空");
        }

        Node<E> old = head;
        head = head.next;
        old.next = null;
        if (head == null){
            tail = null;
        }
        size--;
        return old.data;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            throw new RuntimeException("空");
        }

        return head.data;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        String str = "";
        Node tmp = head;
        while (tmp != null){
            str += tmp.data + "->";
            tmp = tmp.next;
        }
        return str;
    }

    private class Node<E>{
        // 数据域
        public E data;

        // 指针域(指向下一个节点)
        public Node<E> next;

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
