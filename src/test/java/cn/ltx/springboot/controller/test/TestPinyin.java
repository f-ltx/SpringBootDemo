package cn.ltx.springboot.controller.test;

import java.util.ArrayList;
import java.util.List;

public class TestPinyin {


    public static void eachList(List<String[]> list){
        List<String[]> newList = new ArrayList<>();
        for (int i=0;i<list.size()-1;i++){
           newList.add(mergeArray(list.get(i),list.get(i+1)));
        }
        System.out.println(newList);
    }

    public static String[] mergeArray(String[] arrayA,String[] arrayB){
        List<String> list = new ArrayList<>();
        for (int i=0;i<arrayA.length;i++){
            for (int j=0;j<arrayB.length;j++){
                list.add(arrayA[i]+arrayB[j]);
            }
        }
        System.out.println(list);
        return list.toArray(new String[list.size()]);
    }

    public static void main(String[] args) {
        List<String[]> list = new ArrayList<String[]>();

        String[] strings1=new String[]{"tan", "dan"};                   //2
        String[] strings2=new String[]{"xing", "hang", "heng"};         //3
        String[] strings3=new String[]{"shan"};                         //1
//        String[] strings4=new String[]{"hui", "kuai"};                  //2
//        String[] strings5=new String[]{"can", "sen"};                   //2

        list.add(strings1);
        list.add(strings2);
        list.add(strings3);
//        list.add(strings4);
//        list.add(strings5);

        eachList(list);






//        第一次测试
//        for (int i=0;i<strings1.length;i++){
//            StringBuffer sb1 = new StringBuffer();
//            sb1.append(strings1[i]);
//            for (int j=0;j<strings2.length;j++){
//                StringBuffer sb2 = new StringBuffer();
//                sb2.append(sb1).append(strings2[j]);
//                for (int k=0;k<strings3.length;k++){
//                    StringBuffer sb3 = new StringBuffer();
//                    sb3.append(sb2).append(strings3[k]);
//                    for (int l=0;l<strings4.length;l++){
//                        StringBuffer sb4 = new StringBuffer();
//                        sb4.append(sb3).append(strings4[l]);
//                        for (int m=0;m<strings5.length;m++){
//                            StringBuffer sb5 = new StringBuffer();
//                            sb5.append(sb4).append(strings5[m]);
//                            System.out.println(sb5);
//                        }
//                        System.out.println(sb4);
//                    }
//                    System.out.println(sb3);
//                }
//                System.out.println(sb2);
//            }
//        }



    }

}
