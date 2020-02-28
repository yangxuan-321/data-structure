package org.chiefdata.stack;

import org.chiefdata.array.Array;

/**
 * @author : Kevin
 * @Title : ArrayStack
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/26 12:48
 * @Modifyed By :
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<E>(capacity);
    }

    public ArrayStack(){
        array = new Array<E>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public void push(E e) {
        array.add(e);
    }

    @Override
    public E pop() {
        return array.remove();
    }

    @Override
    public E peek() {
        return array.get();
    }

    @Override
    public String toString() {
        return "[" + array.toString() + "] stack-top";
    }
}
