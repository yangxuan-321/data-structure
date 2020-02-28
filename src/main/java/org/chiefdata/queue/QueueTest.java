package org.chiefdata.queue;

import org.chiefdata.array.Array;

/**
 * @author : Kevin
 * @Title : QueueTest
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/26 20:45
 * @Modifyed By :
 */
public class QueueTest {
    public static void main(String[] args) {

//        Queue<Integer> queue = new LinkedListQueue<Integer>();
//
//        for (int i = 0; i < 10; i++) {
//            queue.add(i);
//        }
//
//        System.out.println(queue.toString());
//        queue.poll();
//        System.out.println(queue.toString());

//        Queue<Integer> queue = new LoopQueue<Integer>();
//        //
//        for (int i = 0; i < 20; i++) {
//            queue.add(i);
//        }
//
//        for (int i = 0; i < 4; i++) {
//            queue.poll();
//        }
//
//        queue.poll();
//
//        queue.poll();
//        queue.poll();
//        queue.poll();
//        queue.poll();
//        queue.poll();
//
//        queue.add(16);
//        queue.add(17);
//        queue.add(18);
//
//        System.out.println(queue.toString());
        comp(new ArrayQueue<Integer>());
        comp(new LoopQueue<Integer>());
        comp(new LinkedListQueue<Integer>());
    }

    public static void comp(Queue<Integer> queue){

        System.out.println("-----开始:" + queue.getClass() + "-----");
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            queue.add(i);
        }


        for (int i = 0; i < 100000; i++) {
            queue.poll();
        }

        System.out.println(System.currentTimeMillis() - start);
    }

}
