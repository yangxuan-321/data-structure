package org.chiefdata.queue;

import org.chiefdata.heap.MaxHeap;

import java.util.Comparator;

/**
 * @author : Kevin
 * @Title : PriorityQueue
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/2 14:21
 * @Modifyed By :
 */
public class PriorityQueue<E> implements Queue<E> {
    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        this.maxHeap = new MaxHeap<E>();
    }

    public PriorityQueue(Comparator<E> comparator){
        this.maxHeap = new MaxHeap<E>(comparator);
    }

    @Override
    public void add(E e) {
        maxHeap.add(e);
    }

    @Override
    public E poll() {
        return maxHeap.take();
    }

    @Override
    public E peek() {
        return maxHeap.seeMax();
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
