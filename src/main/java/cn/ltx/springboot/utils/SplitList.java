package cn.ltx.springboot.utils;

import java.util.ArrayList;
import java.util.List;

public class SplitList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i=0;i<500;i++){
            list.add("param" + i);
        }
        System.out.println(list.size());
        System.out.println("------------------");
        List<List<String>> splitList = splitList(list,900);
        System.out.println(splitList.size());
        for (List<String> strings : splitList) {
            System.out.println(strings.size());
        }
    }

    /**
     * Description: 按指定大小分割list
     * @param target    要分割的list
     * @param size      指定大小
     * @author  litianxiang
     * @date    2019/1/15 15:31
     *
     */
    public static List<List<String>> splitList(List<String> target, int size) {
        List<List<String>> listArr = new ArrayList<List<String>>();
        // 获取被拆分的list个数
        int arrSize = target.size() % size == 0 ? target.size() / size : target.size() / size + 1;
        for (int i = 0; i < arrSize; i++) {
            List<String> sub = new ArrayList<String>();
            // 把指定索引数据放入到list中
            for (int j = i * size; j <= size * (i + 1) - 1; j++) {
                if (j <= target.size() - 1) {
                    sub.add(target.get(j));
                }
            }
            listArr.add(sub);
        }
        return listArr;
    }
}
