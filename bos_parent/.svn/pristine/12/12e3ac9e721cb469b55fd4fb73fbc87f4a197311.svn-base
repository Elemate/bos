package com.wq.web.action;

import com.wq.crm.Customer;
import com.wq.crm.CustomerService;
import com.wq.domain.Decidedzone;
import com.wq.domain.Subarea;
import com.wq.service.DecidedzoneService;
import com.wq.service.SubareaService;
import com.wq.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.util.Arrays;
import java.util.List;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

    @Autowired
    public DecidedzoneService decidedzoneService;
    @Autowired
    public SubareaService subareaService;

    public String[] subareaId;

    //调整之后的关联定区id
    public List<Integer> customerIds;

    //调整之前关联定区的id
    public String rawCustomerIds;

    //分页查询所有定区
    public String list(){
        decidedzoneService.pageQuery(pageBean);
        java2Json(pageBean,new String[]{"subareas","decidedzones","currentPage","pageSize","detachedCriteria"});
        return NONE;
    }

    //
    @Autowired
    public CustomerService proxy;
    //查询与分区关系的客户
    public String findListHasAssociation(){
        List<Customer> customers = proxy.findListHasAssociation(model.getId());
        java2Json(customers,new String[]{});
        return NONE;
    }

    //查询与分区没有关系的客户
    public String findListNotAssociation(){
        List<Customer> customers = proxy.findListNotAssociation();
        java2Json(customers,new String[]{});
        return NONE;
    }

    //将与定区关联关系发生改变的客户保存到crm中
    public String assigncustomerstodecidedzone(){
        String[] rawCustomerId = rawCustomerIds.split(",");
        Integer[] intRaw = new Integer[rawCustomerId.length];
        for (int i=0; i<rawCustomerId.length; i++){
             intRaw[i] = Integer.valueOf(rawCustomerId[i]);
        }
        proxy.assigncustomerstodecidedzone(Arrays.asList(intRaw), customerIds, model.getId());
        return LIST;
    }

    //查找与定区关联的分区
    public String associationSubarea(){
        List<Subarea> subareas = decidedzoneService.findListByDecidezoneId(model.getId());
        java2Json(subareas, new String[]{"subareas", "decidedzone"});
        return NONE;
    }



    //保存新增定区信息
    public String save(){
        decidedzoneService.save(model, subareaId);
        return LIST;
    }

    public String[] getSubareaId() {
        return subareaId;
    }

    public void setSubareaId(String[] subareaId) {
        this.subareaId = subareaId;
    }

    public List<Integer> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    public String getRawCustomerIds() {
        return rawCustomerIds;
    }

    public void setRawCustomerIds(String rawCustomerIds) {
        this.rawCustomerIds = rawCustomerIds;
    }
}
