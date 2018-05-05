package com.wq.service.impl;

import com.wq.dao.WorkordermanageDao;
import com.wq.domain.Workordermanage;
import com.wq.service.WorkordermanageService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class WorkordermanageServiceImpl implements WorkordermanageService {

    @Autowired
    private WorkordermanageDao workordermanageDao;

    public void save(Workordermanage model) {
        try {
            workordermanageDao.save(model);
        } catch (Exception e){
            e.printStackTrace();
            ServletActionContext.getResponse().setContentType("html/text;charset=utf-8");
            try {
                ServletActionContext.getResponse().getWriter().print(1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public List<Workordermanage> findAll() {
        return workordermanageDao.findAll();
    }
}
