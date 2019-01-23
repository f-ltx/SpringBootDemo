package cn.ltx.springboot.mapper;

import cn.ltx.springboot.entity.Organ;

public interface OrganMapper {
    int deleteByPrimaryKey(Integer organId);

    int insert(Organ record);

    int insertSelective(Organ record);

    Organ selectByPrimaryKey(Integer organId);

    int updateByPrimaryKeySelective(Organ record);

    int updateByPrimaryKey(Organ record);
}