package cn.ltx.springboot.utils;

import java.util.UUID;

/**
 * Description: uuid工具类
 * @author      litianxiang
 * @date        2019/1/22
 */
public class UuidUtil {
    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }
    public static void main(String[] args) {  
        for (int i = 0; i < 5; i++) {
            System.out.println(UuidUtil.getUuid());
        }  
    }  
} 