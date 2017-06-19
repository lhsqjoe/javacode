package com.primemobi.iaas.other;

import java.util.*;

/**
 * ArrayList、HashSet 查询效率测试
 * 结论：在i5四核处理器 8G内存 机器上测试,结果HashSet执行 contains效率比ArrayList 效率稍高,执行add 方法时ArrayList
 *      效率比HashSet高
 *
 * @Author qiao
 */
public class CollectionEfficiencyTester {
    private static int value = 50000;//测试次数
    private static int count = 10000000;//集合大小



    private static void setup(Collection collection) {
        for (int i = 0; i < count; i++) {
            collection.add(i);
        }
    }

    private static void watchExecute(Collection collection) {
        long startMillis = System.currentTimeMillis();
        collection.contains(value);
        long endtMillis = System.currentTimeMillis();
        System.out.println("==============历时=========" + (endtMillis - startMillis));
    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>();
        System.out.println("==============开始测试list=========");
        setup(list);
        watchExecute(list);
        System.out.println("==============list测试结束=========");
        System.out.println("==============开始测试set=========");
        setup(set);
        watchExecute(set);
        System.out.println("==============set测试结束=========");
    }
}
