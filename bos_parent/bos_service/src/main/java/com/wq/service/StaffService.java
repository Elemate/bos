package com.wq.service;

import com.wq.domain.Staff;
import com.wq.utils.PageBean;

import java.util.List;

public interface StaffService {
    void save(Staff model);

    //分页查询取派员
    void pageQuery(PageBean pageBean);

    void deleteBatch(String id);

    Staff findById(String id);

    void update(Staff staff);

    List<Staff> findNotDelete();
}
