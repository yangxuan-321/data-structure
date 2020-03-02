package org.chiefdata.map;

import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : Kevin
 * @Title : LinkedListMapTest
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/1 15:39
 * @Modifyed By :
 */
public class LinkedListMapTest {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 100000; i++) {
            list.add(random.nextInt(10000));
        }

        System.out.println("LinkedListMap:" + testMapTimeCust(new LinkedListMap<Integer, Integer>(), list));
        System.out.println("BSTMap:" + testMapTimeCust(new BSTMap<Integer, Integer>(), list));
    }

    public static long testMapTimeCust(Map<Integer, Integer> map, List<Integer> list){
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            Integer key = list.get(i);
            Integer value = map.get(key);
            if (null != value){
                map.set(key, value.intValue() + 1);
            }else {
                map.add(key, 1);
            }
        }

        return System.currentTimeMillis() - start;
    }
}
