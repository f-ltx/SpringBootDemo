package cn.ltx.springboot.dao;

import cn.ltx.springboot.entity.Organ;
import org.springframework.stereotype.Component;

@Component
public interface OrganMapper {
    int deleteByPrimaryKey(Integer organId);

    int insert(Organ record);

    int insertSelective(Organ record);

    Organ selectByPrimaryKey(Integer organId);

    int updateByPrimaryKeySelective(Organ record);

    int updateByPrimaryKey(Organ record);
}