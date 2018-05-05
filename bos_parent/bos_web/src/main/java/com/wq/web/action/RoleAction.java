package com.wq.web.action;

import com.wq.domain.Role;
import com.wq.service.RoleService;
import com.wq.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

    @Autowired
    private RoleService roleService;

    private String functionIds;     //角色所授权的Id
    public String add(){

        roleService.save(model, functionIds);
        return LIST;
    }

    public String pageQuery(){

        roleService.pageQuery(pageBean);
        java2Json(pageBean, new String[]{"detachedCriteria","functions","users"});
        return NONE;
    }

    //查找所有角色
    public String listAjax(){
        List<Role> roles = roleService.findAll();
        java2Json(roles, new String[]{"functions","users"});
        return NONE;
    }
    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }


}
