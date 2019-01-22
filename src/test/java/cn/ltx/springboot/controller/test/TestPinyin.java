package cn.ltx.springboot.controller.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestPinyin {

    public static void main(String[] args) {
        List<String[]> list = new ArrayList<>();
        String[] strings1 = new String[]{"hang", "xing","heng"};
        String[] strings2 = new String[]{"tan", "dan"};
        String[] strings3 = new String[]{"can","sen"};
        String[] strings4 = new String[]{"shan"};
        String[] strings5 = new String[]{"hui","kuai"};
        String[] strings6 = new String[]{"hang", "xing","heng"};
        list.add(strings1);
        list.add(strings2);
        list.add(strings3);
        list.add(strings4);
        list.add(strings5);
        list.add(strings6);
        list.add(strings6);
        list.add(strings6);

        //start
        long start = System.currentTimeMillis();
        //用于存储组合结果
        List<String> result = new ArrayList<>();
        //只处理list长度大于等于2的，只有一个元素的不处理。
        if (list.size() < 2) {
            System.out.println("error");
        } else {
            for (int i = 0; i < list.size() - 1; i++) {
                mergeArray(list, i,result);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start + "ms");
        System.out.println(JSON.toJSONString(result));
        System.out.println(result.size());
        //end

    }

    /**
     * Description: 用于组合不同的字符串数组，按照顺序组合
     * @param   list 需要组合的字符串数组
     * @param   index 需要组合的字符串数组索引
     * @param   result 用于存储组合后的字符串数组
     * @author  litianxiang
     * @date    2019/1/14 14:30
     *
     */
    public static void mergeArray(List<String[]> list, int index,List<String> result) {
        String[] arrayA = list.get(index);
        String[] arrayB = list.get(index + 1);
        //临时用于存放组合的数组
        List<String> tempList = new ArrayList<>();
        //1.首先把list中的前两个组合起来
        for (int x = 0; x < arrayA.length; x++) {
            for (int y = 0; y < arrayB.length; y++) {
                tempList.add(arrayA[x] + arrayB[y]);
            }
        }
        //把临时组合好的数组放进newList，用于往下递归调用
        List<String[]> newList = new ArrayList<>();
        newList.add(tempList.toArray(new String[tempList.size()]));
        //2.其次把没有组合的放进newList中
        for (int z = index + 2; z < list.size(); z++) {
            newList.add(list.get(z));
        }
        //3.递归调用，当递归到倒数第二个时，不再递归调用
        if (newList.size() >= 2) {
            mergeArray(newList, 0,result);
        }
        result.addAll(Arrays.asList(newList.get(0)));
    }

}