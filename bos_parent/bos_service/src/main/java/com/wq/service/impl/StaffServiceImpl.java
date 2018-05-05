package com.wq.service.impl;

import com.wq.dao.StaffDao;
import com.wq.domain.Staff;
import com.wq.service.StaffService;
import com.wq.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    public StaffDao staffDao;

    public void save(Staff model) {
        staffDao.save(model);
    }

    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    //批量删除
    public void deleteBatch(String id) {
        staffDao.executeUpdate("staff.deleteBatch",id);
    }

    public Staff findById(String id) {
        return staffDao.findById(id);
    }

    public void update(Staff staff) {
        staffDao.update(staff);
    }

    public List<Staff> findNotDelete() {
        return staffDao.findNotDelete();
    }


}
