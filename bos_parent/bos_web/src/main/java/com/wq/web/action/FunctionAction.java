package com.wq.web.action;

import com.wq.domain.Function;
import com.wq.service.FunctionServcie;
import com.wq.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/*
    权限管理Action
*/

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

    @Autowired
    private FunctionServcie functionServcie;

    public String listAjax(){
        List<Function> functions = functionServcie.findAll();
        java2Json(functions, new String[]{"parentFunction","roles"});
        return NONE;
    }

    //保存新增权限
    public String add(){
        //由于前台父节点id没有选取时，默认为一个空字符串而不是null，所以这里需要校验下
        String parentId = model.getParentFunction().getId();
        if (model.getParentFunction()!=null&& StringUtils.isBlank(parentId)){
            model.setParentFunction(null);
        }
        functionServcie.save(model);
        return LIST;
    }

    //分页查询权限数据
    public String pageQuery(){

        //function中有个page,所以分页查询时，page会被框架赋值给model里面去了
        String page = model.getPage();
        pageBean.setCurrentPage(Integer.parseInt(page));
        functionServcie.pageQuery(pageBean);
        this.java2Json(pageBean,new String[]{"detachedCriteria","roles","children","parentFunction"});
        return NONE;
    }

    //基本功能菜单加载
    public String findMenu(){
        List<Function> functions = functionServcie.findMenu();
        java2Json(functions, new String[]{"roles", "children"});
        return NONE;
    }
}
