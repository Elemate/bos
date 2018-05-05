package com.wq.dao.impl;

import com.wq.dao.StaffDao;
import com.wq.dao.base.impl.BaseDaoImpl;
import com.wq.domain.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {

    public List<Staff> findNotDelete() {
        String hql = "from Staff where deltag =0";
        List<Staff> staffs = (List<Staff>) this.getHibernateTemplate().find(hql);
        return staffs;
    }
}
