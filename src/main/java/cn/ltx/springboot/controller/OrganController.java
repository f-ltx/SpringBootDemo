package cn.ltx.springboot.controller;

import cn.ltx.springboot.entity.Organ;
import cn.ltx.springboot.service.OrganService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/organ")
public class OrganController {
    @Autowired
    private OrganService organService;

    @RequestMapping(value = "/get",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Organ get(
            @RequestParam(name = "id")String id){
        Organ organ = (Organ) organService.selectByPrimaryKey(Integer.parseInt(id)).getData();
        System.out.println(JSON.toJSONString(organ));
        return organ ;
    }

}
