package com.wq.dao;

import com.wq.dao.base.BaseDao;
import com.wq.domain.Staff;

import java.util.List;

public interface StaffDao extends BaseDao<Staff> {
    List<Staff> findNotDelete();
}
