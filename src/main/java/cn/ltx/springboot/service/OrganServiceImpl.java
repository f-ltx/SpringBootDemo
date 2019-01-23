package cn.ltx.springboot.service;

import cn.ltx.springboot.entity.Organ;
import cn.ltx.springboot.mapper.OrganMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganServiceImpl implements OrganService {

    private OrganMapper organMapper;
    @Override
    public List<Organ> findAllOrgans() {
        return organMapper.selectAll();
    }
}
