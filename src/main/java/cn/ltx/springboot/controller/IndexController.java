package cn.ltx.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: 用于首页模块
 * @author      litianxiang
 * @date        2019/1/22
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        System.out.println("index");
        return "index";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(String name){
        System.out.println("get");
        System.out.println(name);
        return "get";
    }

    @RequestMapping("/post")
    @ResponseBody
    public String post(String name){
        System.out.println("post");
        System.out.println(name);
        return "post";
    }
}
