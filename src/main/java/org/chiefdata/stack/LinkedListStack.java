package org.chiefdata.stack;

import org.chiefdata.linkedlist.LinkedList;
import org.chiefdata.linkedlist.LinkedListNoneHead;

/**
 * @author : Kevin
 * @Title : LinkedListStack
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/28 1:02
 * @Modifyed By :
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedListNoneHead<E> list;

    public LinkedListStack(){
        this.list = new LinkedListNoneHead<E>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addForHead(e);
    }

    @Override
    public E pop() {
        return list.remove(0);
    }

    @Override
    public E peek() {
        return list.getData(0);
    }

    @Override
    public String toString() {
        return "head " + list.toString();
    }
}
