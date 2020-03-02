package org.chiefdata.set;

import org.chiefdata.linkedlist.LinkedList;

import java.util.Comparator;

/**
 * @author : Kevin
 * @Title : LinkedListSet
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/1 13:02
 * @Modifyed By :
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    private Comparator<E> comparator;

    public LinkedListSet(){
        this.list = new LinkedList<E>();
    }

    public LinkedListSet(Comparator<E> comparator){
        this.list = new LinkedList<E>(comparator);
    }

    @Override
    public void add(E e) {
        if (list.contains(e)){
            return;
        }

        list.addForHead(e);
    }

    @Override
    public void remove(E e) {
        list.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
