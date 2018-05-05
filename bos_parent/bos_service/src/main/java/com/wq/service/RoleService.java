package com.wq.service;

import com.wq.domain.Role;
import com.wq.utils.PageBean;

import java.util.List;

public interface RoleService {
    void save(Role model, String functionIds);

    void pageQuery(PageBean pageBean);

    List<Role> findAll();
}
