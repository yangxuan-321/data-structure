package org.chiefdata.heap;

import org.chiefdata.array.Array;

import java.util.Comparator;

/**
 * @author : Kevin
 * @Title : MaxHeap
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/2 9:59
 * @Modifyed By :
 */
public class MaxHeap<E> {
    private Comparator<E> comparator;

    private Array<E> data;

    public MaxHeap(Comparator<E> comparator, int capacity){
        this.comparator = comparator;
        data = new Array<E>(capacity);
    }

    public MaxHeap(Comparator<E> comparator){
        this.comparator = comparator;
        data = new Array<E>();
    }

    public MaxHeap(){
        this.comparator = (e1, e2)->{
            if (e1 instanceof Comparable){
                return ((Comparable) e1).compareTo(e2);
            }else {
                return e1.hashCode() - e2.hashCode();
            }
        };
        data = new Array<E>();
    }

    public int getSize(){
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
     * @param childIndex
     * @return
     */
    private int parent(int childIndex){
        if (childIndex < 0){
            throw new RuntimeException("错误的参数");
        }

        if (childIndex == 0){
            throw new RuntimeException("index-0[根节点],没有父亲节点");
        }
        return (childIndex - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左儿子节点的索引
     * @param parentIndex
     * @return
     */
    private int leftChild(int parentIndex){
        return (parentIndex * 2) + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右儿子节点的索引
     * @param parentIndex
     * @return
     */
    private int rightChild(int parentIndex){
        return (parentIndex * 2) + 2;
    }

    public void add(E e){
        data.add(e);
        siftUp(getSize() - 1);
    }

    private void siftUp(int k){
        //如果当前 k 是合法的，且 k 不为根节点(根节点不予任何节点交换)。且 k节点比父亲节点 要大的话，就需要做 交换。因为
        while (k > 0 && comparator.compare(data.get(parent(k)), data.get(k)) < 0){
//            E e = data.get(parent(k));
//            data.set(parent(k), data.get(k));
//            data.set(k, e);
            // 和父亲节点进行交换
            data.swap(parent(k), k);
            // 然后索引 变成父亲节点的索引
            k = parent(k);
        }
    }

    /**
     * 取出堆顶元素
     */
    public E take(){
        data.swap(0, getSize()-1);
        E max = data.remove();

        siftDown(0);
        return max;
    }

    private void siftDown(int k){
        //如果当前 k 的左节点 是合法的，也就是说存在左节点(左节点一定比右节点大，所以左节点一定比)。
        while (leftChild(k) < getSize()){
            int leftChild = leftChild(k);
            int rightChild = rightChild(k);

            int maxEleIndex = leftChild;
            if (rightChild < getSize()){
                // 找出左右孩子中最大的
                maxEleIndex = comparator.compare(data.get(leftChild), data.get(rightChild)) < 0 ? rightChild : leftChild;
            }else {
                // 找出左右孩子中最大的 如果不存在右孩子，则直接将左孩子作为最大孩子。
                maxEleIndex = leftChild;
            }
            // 将最大的孩子和当前元素进行比较
            int compare = comparator.compare(data.get(k), data.get(maxEleIndex));
            if (compare >= 0){// 如果 当前比最大子节点大 则停止下沉 相等也下沉。毕竟在实现优先队列时候 同一优先级的 先到先得
                break;
            }
            // 如果 当前比最大子节点小 则交换
            data.swap(maxEleIndex, k);
            // 然后索引 变成父亲节点的索引
            k = maxEleIndex;
        }
    }

    public E seeMax(){
        if (getSize() == 0){
            throw new RuntimeException("空的");
        }
        return data.get(0);
    }
}
