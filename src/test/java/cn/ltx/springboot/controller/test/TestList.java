package cn.ltx.springboot.controller.test;

import cn.ltx.springboot.entity.User;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
//        String[] arrayA = {"XINGDANSHENSHAN", "HENGTANSHENSHAN", "HANGDANCANSHAN", "HANGDANSHENSHAN", "XINGTANSHENSHAN", "HANGTANSHENSHAN", "HANGTANCANSHAN", "HENGDANCANSHAN", "XINGDANCANSHAN", "HENGDANSHENSHAN", "XINGTANCANSHAN", "HENGTANCANSHAN", "CANSHAN", "SHENSHAN", "SHAN", "HENGTANSHEN", "HANGTANCAN", "XINGDANCAN", "HENGDANCAN", "HANGTANSHEN", "XINGDANSHEN", "HENGTANCAN", "HANGDANSHEN", "XINGTANCAN", "HENGDANSHEN", "XINGTANSHEN", "HANGDANCAN", "HENG", "HANG", "XING", "HUI", "KUAI", "DAN", "TAN", "DANSHENSHANKUAI", "DANCANSHANHUI", "DANCANSHANKUAI", "TANCANSHANKUAI", "TANSHENSHANKUAI", "TANSHENSHANHUI", "DANSHENSHANHUI", "TANCANSHANHUI", "DANCAN", "DANSHEN", "TANSHEN", "TANCAN", "CANSHANKUAI", "CANSHANHUI", "SHENSHANKUAI", "SHENSHANHUI", "HANGTANSHENSHANKUAI", "XINGDANSHENSHANKUAI", "XINGTANCANSHANHUI", "HANGTANCANSHANHUI", "HANGDANSHENSHANKUAI", "HENGDANSHENSHANHUI", "HENGDANSHENSHANKUAI", "XINGTANCANSHANKUAI", "HANGDANCANSHANKUAI", "HANGTANCANSHANKUAI", "HENGDANCANSHANHUI", "HENGTANSHENSHANKUAI", "HANGTANSHENSHANHUI", "HANGDANCANSHANHUI", "HENGTANCANSHANHUI", "XINGTANSHENSHANKUAI", "HANGDANSHENSHANHUI", "HENGTANCANSHANKUAI", "XINGDANSHENSHANHUI", "XINGDANCANSHANHUI", "HENGDANCANSHANKUAI", "XINGTANSHENSHANHUI", "XINGDANCANSHANKUAI", "HENGTANSHENSHANHUI", "SHANHUI", "SHANKUAI", "HANGDAN", "HENGDAN", "XINGDAN", "XINGTAN", "HANGTAN", "HENGTAN", "DANCANSHAN", "TANCANSHAN", "TANSHENSHAN", "DANSHENSHAN", "SHEN", "CAN"};
//        String[] arrayB = {"hangdancanshanhui", "hangdancanshankuai", "hangdanshenshanhui", "hangdanshenshankuai", "hangtancanshanhui", "hangtancanshankuai", "hangtanshenshanhui", "hangtanshenshankuai", "hengdancanshanhui", "hengdancanshankuai", "hengdanshenshanhui", "hengdanshenshankuai", "hengtancanshanhui", "hengtancanshankuai", "hengtanshenshanhui", "hengtanshenshankuai", "xingdancanshanhui", "xingdancanshankuai", "xingdanshenshanhui", "xingdanshenshankuai", "xingtancanshanhui", "xingtancanshankuai", "xingtanshenshanhui", "xingtanshenshankuai", "hangdancanshan", "hangdanshenshan", "hangtancanshan", "hangtanshenshan", "hengdancanshan", "hengdanshenshan", "hengtancanshan", "hengtanshenshan", "xingdancanshan", "xingdanshenshan", "xingtancanshan", "xingtanshenshan", "hangdancan", "hangdanshen", "hangtancan", "hangtanshen", "hengdancan", "hengdanshen", "hengtancan", "hengtanshen", "xingdancan", "xingdanshen", "xingtancan", "xingtanshen", "hangdan", "hangtan", "hengdan", "hengtan", "xingdan", "xingtan", "dancanshanhui", "dancanshankuai", "danshenshanhui", "danshenshankuai", "tancanshanhui", "tancanshankuai", "tanshenshanhui", "tanshenshankuai", "dancanshan", "danshenshan", "tancanshan", "tanshenshan", "dancan", "danshen", "tancan", "tanshen", "canshanhui", "canshankuai", "shenshanhui", "shenshankuai", "canshan", "shenshan", "shanhui", "shankuai"};
//
//        System.out.println(arrayA.length);
//        System.out.println(arrayB.length);
//        for (int i = 0; i < arrayA.length; i++) {
//            if (!isContains(arrayA[i], arrayB)) {
//                System.out.println(arrayA[i]);
//            }
//        }

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        User user = new User();
        user.setTags(list);
        System.out.println(JSON.toJSONString(user.getTags()));

        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        user.getTags().addAll(list1);
        System.out.println(JSON.toJSONString(user.getTags()));

    }

    public static boolean isContains(String value, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (value.equalsIgnoreCase(array[i])) {
                return true;
            }
        }
        return false;
    }
}
