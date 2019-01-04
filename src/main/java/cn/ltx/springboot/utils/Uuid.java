package cn.ltx.springboot.utils;

import java.util.UUID;
  
public class Uuid {
    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    public static void main(String[] args) {  
        for (int i = 0; i < 5; i++) {
            System.out.println(Uuid.getUuid());
        }  
    }  
} 