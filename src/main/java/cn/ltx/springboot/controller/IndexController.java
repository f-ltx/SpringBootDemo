package cn.ltx.springboot.controller;

import ch.qos.logback.classic.Logger;
import cn.ltx.springboot.utils.Utils3DES;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 用于首页模块
 *
 * @author litianxiang
 * @date 2019/1/22
 */
@Controller
public class IndexController {
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        System.out.println("index");
        return "index";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(String name) {
        System.out.println("get");
        System.out.println(name);
        return "get";
    }

    @RequestMapping("/post")
    @ResponseBody
    public String post(String name) {
        System.out.println("post");
        System.out.println(name);
        return "post";
    }

    @RequestMapping("/syncJgjCase")
    @ResponseBody
    public String syncJgjCase(HttpServletRequest request, String method, String datas) {
        System.out.println(datas);
        logger.debug("post----------------------------------------------------");
        String data = Utils3DES.decryptString(datas);
        Map<String, Object> map = new HashMap<>();
        map.put("method", method);
        map.put("datas", data);
        logger.debug(String.valueOf(map));
        return String.valueOf(map);
    }

}
