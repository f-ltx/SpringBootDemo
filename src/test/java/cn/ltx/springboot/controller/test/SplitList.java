package cn.ltx.springboot.controller.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class SplitList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        System.out.println(list.size());
        System.out.println("---------------------");
        for (int i=0;i<10;i++){
            list.add("param" + i);
        }
        System.out.println(JSON.toJSONString(list));
//        System.out.println(list.size());
//        System.out.println("------------------");
//        List<List<String>> splitList = splitList(list,900);
//        System.out.println(splitList.size());
//        for (List<String> strings : splitList) {
//            System.out.println(strings.size());
//        }

        StringBuffer sql = new StringBuffer("select * from table_name where name = ''") ;
        if(list.size()>0){
            sql.append(" and r.tradPiny in ('");
            for (String s : list) {
                sql.append(s).append("','").append(s.toUpperCase()).append("','");
            }
            sql.delete(sql.lastIndexOf(","),sql.length()).append(")");
            System.out.println(JSON.toJSONString(sql));
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
