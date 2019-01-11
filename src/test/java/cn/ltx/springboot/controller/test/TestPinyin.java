package cn.ltx.springboot.controller.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class TestPinyin {

    public static void main(String[] args) {
        List<String[]> list = new ArrayList<String[]>();
        String[] strings1 = new String[]{"A", "B"};
        String[] strings2 = new String[]{"a", "b", "c"};
        String[] strings3 = new String[]{"@"};
        String[] strings4 = new String[]{"1", "2", "3"};

        list.add(strings1);
        list.add(strings2);
        list.add(strings3);
        list.add(strings4);

        List<String[]> result = new ArrayList<>();

        if (list.size() < 2) {
            System.out.println("error");
        } else if (list.size() == 2) {
            mergeTwo(list.get(0), list.get(1),result);
        } else {
            for (int i = 0; i < list.size() - 2; i++) {
                mergeMany(list, i,result);
            }
        }

        System.out.println(JSON.toJSONString(result));
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
