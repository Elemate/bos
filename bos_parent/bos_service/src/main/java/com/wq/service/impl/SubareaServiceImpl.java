package com.wq.service.impl;

import com.wq.dao.SubareaDao;
import com.wq.domain.Subarea;
import com.wq.service.SubareaService;
import com.wq.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {

    @Autowired
    public SubareaDao subareaDao;

    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    public void save(Subarea model) {
        subareaDao.save(model);
    }

    public List<Subarea> findAll() {
        return subareaDao.findAll();
    }

    public List<Subarea> findListNotAssociation() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
        criteria.add(Restrictions.isNull("decidedzone"));
        return subareaDao.findByCriteria(criteria);
    }

    public List<Object> findProvinceByGroup() {
        return subareaDao.findProvinceByGroup();
    }

}
