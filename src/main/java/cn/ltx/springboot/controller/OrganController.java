package cn.ltx.springboot.controller;

import cn.ltx.springboot.service.OrganService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/organ")
public class OrganController {
    @Autowired
    private OrganService organService;

    @RequestMapping("/findAllOrgans")
    public void findAllOrgans(){
        organService.findAllOrgans();
//        System.out.println(JSON.toJSONString(organService.findAllOrgans()));
    }

}
