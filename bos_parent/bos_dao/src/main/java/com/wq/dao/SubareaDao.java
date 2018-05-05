package com.wq.dao;

import com.wq.dao.base.BaseDao;
import com.wq.domain.Subarea;

import java.util.List;

public interface SubareaDao extends BaseDao<Subarea> {
    List<Object> findProvinceByGroup();
}
