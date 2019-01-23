package cn.ltx.springboot.mapper;

import cn.ltx.springboot.entity.Organ;

import java.util.List;

public interface OrganMapper {
    int deleteByPrimaryKey(Integer organId);

    int insert(Organ record);

    int insertSelective(Organ record);

    Organ selectByPrimaryKey(Integer organId);

    List<Organ> selectAll();

    int updateByPrimaryKeySelective(Organ record);

    int updateByPrimaryKey(Organ record);
}