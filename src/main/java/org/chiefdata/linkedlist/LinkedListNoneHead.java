package org.chiefdata.linkedlist;

/**
 * @author : Kevin
 * @Title : LinkedList
 * @ProjectName data-structure
 * @Description : 虚拟 头结点(里面不存储数据) 这样真正的头结点 也有了 前驱节点  此时的虚拟头结点 也称为 dummyHead
 * @Time : Created in 2020/2/27 15:19
 * @Modifyed By :
 */
public class LinkedListNoneHead<E> {
    private Node<E> dummyHead;
    private Node<E> tail;
    private int size;

    public LinkedListNoneHead(){
        this.dummyHead = new Node<E>();
        this.size = 0;
    }

    public LinkedListNoneHead(E ... es){
        this();
        for (E e: es) {
            addForTail(e);
        }
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty(){
        return 0 == size;
    }

    public void addForHead(E e){
        // 下面3句可以合为1句 head = new Node(e, head);

        Node<E> node = new Node<E>(e);
        node.next = dummyHead.next;
        dummyHead.next = node;
        size ++;
    }

    public void addForTail(E e){
        Node<E> node = new Node<E>(e);

        Node tmp = dummyHead;
        while (tmp.next != null){
            tmp = tmp.next;
        }

        size ++;
        tmp.next = node;
    }

    public void add(int index, E e){
        if (index > size){
            throw new RuntimeException("越界");
        }

        Node prev = get(index - 1);
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;

        size++;
    }

    public Node<E> get(int index){
        if (index >= size){
            throw new RuntimeException("越界");
        }

        Node<E> tmp = dummyHead.next;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }

        return tmp;
    }

    public E getData(int index){
        if (index >= size){
            throw new RuntimeException("越界");
        }

        Node<E> tmp = dummyHead.next;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }

        return tmp.data;
    }

    public E remove(int index){
        Node tmp = dummyHead;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }

        Node<E> old = tmp.next;
        tmp.next = old.next;
        old.next = null;

        size--;
        return old.data;
    }

    @Override
    public String toString() {
        String str = "";
        Node tmp = dummyHead.next;
        while (tmp != null){
            str = str + tmp.data + "->";
            tmp = tmp.next;
        }

        return str + "NULL";
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


