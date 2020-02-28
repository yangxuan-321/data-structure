package org.chiefdata.stack;

/**
 * @author : Kevin
 * @Title : Stack
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/26 12:17
 * @Modifyed By :
 */
public interface Stack<E> {

    public int getSize();

    public boolean isEmpty();

    public void push(E e);

    public E pop();

    public E peek();
}
