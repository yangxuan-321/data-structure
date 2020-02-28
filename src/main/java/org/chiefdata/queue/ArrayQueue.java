package org.chiefdata.queue;

import org.chiefdata.array.Array;

/**
 * @author : Kevin
 * @Title : ArrayQueue
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/26 20:41
 * @Modifyed By :
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(){
        this.array = new Array<E>();
    }

    public ArrayQueue(int capacity){
        this.array = new Array<E>(capacity);
    }

    @Override
    public void add(E e) {
        array.add(array.getSize(), e);
    }

    /**
     * 此处 出队的 操作 时间复杂度 是O(n)
     * 因为 每次 都需要 移动数组内容。 因此 这样 每次出队 当队列的容量过大时，并不好。因此 需药 引入 队列的新实现方式 -> 循环队列
     * 或者 用 链表
     * @return
     */
    @Override
    public E poll() {
        return array.remove(0);
    }

    @Override
    public E peek() {
        return array.get(0);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        return "queue-head <- [" + array.toString() + "] <- queue-tail";
    }
}
