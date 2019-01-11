package cn.ltx.springboot.controller.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class TestPinyin {

    public static void main(String[] args) {
        System.out.println("行弹参山会");
        long start = System.currentTimeMillis();
        List<String[]> list = new ArrayList<>();
        String[] strings1 = new String[]{"hang", "xing","heng"};
        String[] strings2 = new String[]{"tan", "dan"};
        String[] strings3 = new String[]{"can","sen"};
        String[] strings4 = new String[]{"shan"};
        String[] strings5 = new String[]{"hui","kuai"};

        list.add(strings1);
        list.add(strings2);
        list.add(strings3);
        list.add(strings4);
        list.add(strings5);

        List<String[]> result = new ArrayList<>();

        if (list.size() < 2) {
            System.out.println("error");
        } else {
            for (int i = 0; i < list.size() - 1; i++) {
                if (i==list.size()-2){
                    mergeTwo(list.get(i), list.get(i+1),result);
                }else{
                    mergeMany(list, i,result);
                }
            }
        }

        int sum = 0;
        for (int i=0;i<result.size();i++){
            sum = sum + result.get(i).length;
            System.out.println(JSON.toJSONString(result.get(i)));
        }
        System.out.println("共计"+sum+"条记录。");
        long end = System.currentTimeMillis();
        System.out.println(end-start + "ms");
    }

    public static void mergeMany(List<String[]> list, int index,List<String[]> result) {
        String[] arrayA = list.get(index);
        String[] arrayB = list.get(index + 1);
        List<String> tempList = new ArrayList<>();
        for (int x = 0; x < arrayA.length; x++) {
            for (int y = 0; y < arrayB.length; y++) {
                tempList.add(arrayA[x] + arrayB[y]);
            }
        }
        List<String[]> newList = new ArrayList<>();
        newList.add(tempList.toArray(new String[tempList.size()]));
        for (int z = index + 2; z < list.size(); z++) {
            newList.add(list.get(z));
        }
        if (newList.size() > 2) {
            mergeMany(newList, 0,result);
        } else {
            mergeTwo(newList.get(0), newList.get(1),result);
        }
        result.add(newList.get(0));
    }

    public static void mergeTwo(String[] arrayA, String[] arrayB,List<String[]> result) {
        List<String> tempList = new ArrayList<>();
        for (int x = 0; x < arrayA.length; x++) {
            for (int y = 0; y < arrayB.length; y++) {
                tempList.add(arrayA[x] + arrayB[y]);
            }
        }
        result.add(tempList.toArray(new String[1]));
    }
}
