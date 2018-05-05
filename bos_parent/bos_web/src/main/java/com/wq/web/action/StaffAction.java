package com.wq.web.action;

import com.wq.domain.Staff;
import com.wq.service.StaffService;
import com.wq.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

    @Autowired
    public StaffService staffService;
    private String ids; //批量删除职员的id


    //查询职员
    public String pageQuery() throws IOException {
        //获取pageBean全部数据
        staffService.pageQuery(pageBean);

        java2Json(pageBean,new String[]{"currentPage","decidedzones","pageSize","detachedCriteria"});
        return NONE;
    }

    //新增职员
    public String add(){
        staffService.save(model);
        return LIST;
    }

    @RequiresPermissions("staff-delete")
    public String delete(){
        //判断删除id非空
        if(StringUtils.isNotBlank(ids)){
            String[] staffId = ids.split(",");
            for(String id : staffId){
                staffService.deleteBatch(id);
            }
        }
        return LIST;
    }

    //更新取派员信息
    public String edit(){

        //代码级别的权限控制，一般不建议使用
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission("staff-edit");

        Staff staff = staffService.findById(model.getId());
        staff.setDeltag(model.getDeltag());
        staff.setHaspda(model.getHaspda());
        staff.setName(model.getName());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());
        staff.setTelephone(model.getTelephone());

        //保存更新信息
        staffService.update(staff);
        return LIST;
    }

    //查询未删除的取派员
    public String listAjax(){
        List<Staff> staffs = staffService.findNotDelete();
        java2Json(staffs, new String[]{"decidedzones"});
        return NONE;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
