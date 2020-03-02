package org.chiefdata.heap;

import java.util.Random;

/**
 * @author : Kevin
 * @Title : HeapTest
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/2 13:14
 * @Modifyed By :
 */
public class HeapTest {
    public static void main(String[] args) {
        maxHeap();
    }

    public static void maxHeap(){
        int count = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            maxHeap.add(random.nextInt(count));
        }

        for (int i = 0; i < count; i++) {
            System.out.print(maxHeap.take() + "->");
        }
        System.out.println();
    }
}
