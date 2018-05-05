package com.wq.web.action;

import com.wq.crm.Customer;
import com.wq.crm.CustomerService;
import com.wq.domain.Noticebill;
import com.wq.service.NoticebillService;
import com.wq.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/*
* 业务通知单Action
* */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

    @Autowired
    private CustomerService proxy;
    @Autowired
    private NoticebillService noticebillService;

    //根据电话号码查询客户信息
    public String findCustomerByTelephone(){
        System.out.println("aaaaaaaaaaaaaaaaaaaaa");
        Customer customer = proxy.findCustomerByTelephone(model.getTelephone());
        java2Json(customer, new String[]{});

        return NONE;
    }

    //新增业务通知单
    public String save(){
        noticebillService.save(model);
        return "noticebill_add";
    }
}
