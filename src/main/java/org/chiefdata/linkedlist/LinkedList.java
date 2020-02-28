package org.chiefdata.linkedlist;

/**
 * @author : Kevin
 * @Title : LinkedList
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/27 15:19
 * @Modifyed By :
 */
public class LinkedList<E> {
    private Node<E> head;
    private int size;

    public LinkedList(){
        this.head = null;
        this.size = 0;
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
        node.next = head;
        head = node;
        size ++;
    }

    public void addForTail(E e){
        size ++;
        Node<E> node = new Node<>(e);
        if (this.head == null){
            this.head = node;
            return;
        }

        Node tmp = head;
        while (true){
            if (tmp.next == null){
                break;
            }
            tmp = tmp.next;
        }

        tmp.next = node;
    }

    public void add(int index, E e){
        if (index == 0){
            addForHead(e);
            return;
        }

        if (index > size){
            throw new RuntimeException("越界");
        }

        Node prev = get(index - 1);
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;
    }

    public Node<E> get(int index){
        if (index >= size){
            throw new RuntimeException("越界");
        }

        Node<E> tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }

        return tmp;
    }

    public LinkedList(E ... es){
        for (E e: es) {
            addForTail(e);
        }
    }

    @Override
    public String toString() {
        String str = "";
        Node tmp = head;
        while (true){
            if (tmp == null){
                break;
            }

            str = str + tmp.data + "->";
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


