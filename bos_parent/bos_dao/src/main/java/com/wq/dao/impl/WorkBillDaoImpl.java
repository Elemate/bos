package com.wq.dao.impl;

import com.wq.dao.WorkBillDao;
import com.wq.dao.base.impl.BaseDaoImpl;
import com.wq.domain.Workbill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkBillDaoImpl extends BaseDaoImpl<Workbill> implements WorkBillDao{
    public List<Workbill> findNewWorkbills() {
        String type = Workbill.TYPE_1;
        String hql = "from Workbill where type = ?";
        List<Workbill> workbills = (List<Workbill>) this.getHibernateTemplate().find(hql, type);
        return workbills;
    }
}
