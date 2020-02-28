package org.chiefdata.queue;

/**
 * @author : Kevin
 * @Title : LoopQueue
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/26 21:06
 * @Modifyed By :
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity){
        // 为什么会有 +1 是因为 循环队列。我们会 一直 冗余 一个 数组的格子不用
        this.data = (E[])new Object[capacity+1];
        this.front = 0;
        this.tail = 0;
    }

    public LoopQueue(){
        this(16);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    private void resize(int newCapacity){
        // 记得 +1
        E[] newData = (E[])new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            // front 有偏移，所以要加上偏移。但是 是循环队列 所以要记得 对数组长度取余
            newData[i] = data[(i+front)%data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public void add(E e) {
        //1.查看队列是否 满容量
        if ((tail+1)%data.length == front){
            resize(getCapacity() * 2);
        }

        data[tail] = e;

        // 本来只用 +1 但是 是循环队列 因此就需要 取余
        tail = (tail+1)%data.length;
        size++;
    }

    @Override
    public E poll() {
        if (isEmpty()){
            throw new RuntimeException("队列为空,不能poll");
        }

        E rm = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size --;

        if (size == getCapacity()/4 && getCapacity()/2 != 0){
            resize(getCapacity()/2);
        }

        return rm;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            throw new RuntimeException("队列为空,不能peek");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public String toString() {

        String s = "";
        for (int i = front; i != tail; i = (i+1) % data.length) {
            s += "," + data[i];
        }

        s = "".equals(s) ? "" : s.substring(1);

        return "queue-head <- [" + s + "] <- queue-tail";
    }
}
