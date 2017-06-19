package com.primemobi.iaas.other;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ArrayList、HashSet 查询效率测试
 * 结论：在i5四核处理器 8G内存 机器上测试,结果HashSet 比ArrayList 效率稍快
 */
public class CollectionEfficiency {

    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        GenerateSj instance = GenerateSj.getInstance();
        watchExecute(instance,"loopList");
        watchExecute(instance,"loopSet");
    }
    static void watchExecute(GenerateSj instance,String methodName){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long startMillis = System.currentTimeMillis();
        String startDate = dateFormat.format(startMillis);
        System.out.println("=============="+methodName+"开始了=========" + startDate);
        if(methodName.endsWith("Set")){
            instance.loopSet();
        }else if(methodName.endsWith("List")){
            instance.loopList();
        }
        long endtMillis = System.currentTimeMillis();
        String endDate = dateFormat.format(endtMillis);
        System.out.println("==============结束了"+methodName+"=========" + endDate);
        System.out.println("=============="+methodName+"历时=========" + (endtMillis - startMillis));
    }
}

class GenerateSj {
    private static int value = 50000;
    private static GenerateSj sjObject = new GenerateSj();//单例饿汉模式
    private List<Integer> list = new ArrayList<Integer>();
    private Set<Integer> set = new HashSet<Integer>();
    private GenerateSj() {
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
            set.add(i);
        }
    }
    public static GenerateSj getInstance() {
        return sjObject;
    }
    public void loopSet() {
        set.contains(value);
    }

    public void loopList() {
        list.contains(value);
    }

}