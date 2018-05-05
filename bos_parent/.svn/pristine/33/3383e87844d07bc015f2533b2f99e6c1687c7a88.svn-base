package com.wq.service.impl;

import com.wq.crm.CustomerService;
import com.wq.dao.DecidedzoneDao;
import com.wq.dao.NoticebillDao;
import com.wq.dao.WorkBillDao;
import com.wq.domain.*;
import com.wq.service.DecidedzoneService;
import com.wq.service.NoticebillService;
import com.wq.utils.BOSUtils;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService {

    @Autowired
    private NoticebillDao noticebillDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DecidedzoneDao decidedzoneDao;
    @Autowired
    private WorkBillDao workbillDao;
    //保存新增通知单信息
    public void save(Noticebill model) {
        //获得当前登录客户
        User user = BOSUtils.getLoginUser();
        model.setUser(user);
        noticebillDao.save(model);

        //获取客户地址，然后远程调用crm,根据地址获取定区id,再通过定区id指定取派员
        String address = model.getPickaddress();
        String decidezoneId = customerService.findDecidezoneIdByAddress(address);

        //如果定区id为空，则设置为人工分单
        if (decidezoneId!=null){
            Decidedzone decidedzone = decidedzoneDao.findById(decidezoneId);
            Staff staff = decidedzone.getStaff();
            model.setStaff(staff);
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);      //自动分单
            //指定了取派员，为取派员创建工单
            Workbill workbill = new Workbill();
            workbill.setAttachbilltimes(0);
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            workbill.setPickstate(Workbill.PICKSTATE_NO);
            workbill.setStaff(staff);   //为工单关联取派员
            workbill.setRemark(model.getRemark());
            workbill.setType(Workbill.TYPE_1);
            workbill.setNoticebill(model);  //关联通知单
            workbillDao.save(workbill);
        } else {
            //没有查询到定区id就人工分单
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }

    }
}
