package cn.ltx.springboot.service;

import cn.ltx.springboot.common.CodeMessageEnum;
import cn.ltx.springboot.common.ServiceResult;
import cn.ltx.springboot.entity.Organ;
import cn.ltx.springboot.dao.OrganMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Description: 组织机构service
 * @author      litianxiang
 * @date        2019/1/24
 */
@Service
public class OrganServiceImpl implements OrganService {
    @Autowired
    private OrganMapper organMapper;
    @Override
    public ServiceResult selectByPrimaryKey(Integer id) {
        return ServiceResult.success(organMapper.selectByPrimaryKey(id));
    }
}
