package cn.ltx.springboot.service;

import cn.ltx.springboot.common.ServiceResult;
import cn.ltx.springboot.entity.Organ;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrganService {
    ServiceResult selectByPrimaryKey(Integer id);
}
