package com.wq.dao;

import com.wq.dao.base.BaseDao;
import com.wq.domain.Workbill;

import java.util.List;

public interface WorkBillDao extends BaseDao<Workbill> {
    List<Workbill> findNewWorkbills();
}
