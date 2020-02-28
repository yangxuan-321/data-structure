package org.chiefdata.recursive;

/**
 * @author : Kevin
 * @Title : Sum
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/28 13:11
 * @Modifyed By :
 */
public class ArrSum {
    public static void main(String[] args) {
        int[] is = new int[10];
        for (int i = 0; i < is.length; i++) {
            is[i] = i;
        }
        System.out.println(sum(is));
    }

    public static int sum(int[] arrs){
        return sum(arrs, 0);
    }

    /**
     * 计算 start
     * @param arrs
     * @param start
     * @return
     */
    private static int sum(int[] arrs, int start){
//        if (start == arrs.length){
//            return 0;
//        }
        //或者
        if (start == arrs.length - 1){
            return arrs[start];
        }

        return arrs[start] + sum(arrs, start + 1);
    }
}
