package com.wq.service;

import com.wq.domain.Subarea;
import com.wq.utils.PageBean;

import java.util.List;

public interface SubareaService {
    void pageQuery(PageBean pageBean);
    void save(Subarea model);
    List<Subarea> findAll();

    List<Subarea> findListNotAssociation();

    List<Object> findProvinceByGroup();
}
