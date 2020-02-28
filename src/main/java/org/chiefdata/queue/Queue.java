package org.chiefdata.queue;

/**
 * @author : Kevin
 * @Title : Queue
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/26 20:34
 * @Modifyed By :
 */
public interface Queue<E> {
    /**
     * 入队列
     * @param e
     */
    public void add(E e);

    /**
     * 出队列
     * @return
     */
    public E poll();

    /**
     * 查看队首元素
     * @return
     */
    public E peek();

    public int getSize();

    public boolean isEmpty();
}
