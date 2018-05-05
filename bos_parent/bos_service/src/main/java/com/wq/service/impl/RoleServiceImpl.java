package com.wq.service.impl;

import com.wq.dao.RoleDao;
import com.wq.domain.Function;
import com.wq.domain.Role;
import com.wq.service.RoleService;
import com.wq.utils.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public void save(Role model, String functionIds) {

        //保存新角色
        roleDao.save(model);
        //获取所有权限对象
        if(StringUtils.isNotBlank(functionIds)){
            String[] ids = functionIds.split(",");
            for (String id : ids){
                Function function = new Function(id);
                model.getFunctions().add(function);
            }
        }

    }

    public void pageQuery(PageBean pageBean) {
       roleDao.pageQuery(pageBean);
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }
}