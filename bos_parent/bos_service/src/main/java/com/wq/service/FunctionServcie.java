package com.wq.service;

import com.wq.domain.Function;
import com.wq.utils.PageBean;

import java.util.List;

public interface FunctionServcie {
    List<Function> findAll();

    void save(Function model);

    void pageQuery(PageBean pageBean);

    List<Function> findMenu();
}
