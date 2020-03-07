package org.chiefdata.tree.unionfind;

import java.util.Random;

/**
 * @author : Kevin
 * @Title : TestUnionFind
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/3/7 10:55
 * @Modifyed By :
 */
public class TestUnionFind {
    public static void main(String[] args) {
        System.out.println("uf5: " + testUnionFind(new UnionFind5(10000000), 10000000));
        System.out.println("uf4: " + testUnionFind(new UnionFind4(10000000), 10000000));
        System.out.println("uf3: " + testUnionFind(new UnionFind3(10000000), 10000000));
//        System.out.println("uf2: " + testUnionFind(new UnionFind2(10000000), 10000000));
//        System.out.println("uf1: " + testUnionFind(new UnionFind1(1000000), 100000));
    }

    private static double testUnionFind(UnionFind uf, int m){
        int size = uf.getSize();
        Random random = new Random();
        
        long startTime = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);

            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);

            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}
