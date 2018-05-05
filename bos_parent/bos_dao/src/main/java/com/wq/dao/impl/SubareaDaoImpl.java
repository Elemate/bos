package com.wq.dao.impl;

import com.wq.dao.SubareaDao;
import com.wq.dao.base.impl.BaseDaoImpl;
import com.wq.domain.Subarea;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements SubareaDao {


    public List<Object> findProvinceByGroup() {
        String hql = "select r.province, count(*) from Subarea s left outer join s.region r group by r.province";
        List<Object> list = (List<Object>) this.getHibernateTemplate().find(hql);
        return list;
    }
}
