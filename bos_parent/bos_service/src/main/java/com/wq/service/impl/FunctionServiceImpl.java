package com.wq.service.impl;

import com.wq.dao.FunctionDao;
import com.wq.domain.Function;
import com.wq.domain.User;
import com.wq.service.FunctionServcie;
import com.wq.utils.BOSUtils;
import com.wq.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionServcie {

    @Autowired
    private FunctionDao functionDao;

    public List<Function> findAll() {
        return functionDao.findAll();
    }

    public void save(Function model) {
        functionDao.save(model);
    }

    public void pageQuery(PageBean pageBean) {
        functionDao.pageQuery(pageBean);
    }

    public List<Function> findMenu() {
        User user = BOSUtils.getLoginUser();
        if (user.getUsername().equals("admin")){
            return functionDao.findMenu();
        } else {
            return functionDao.findMenuByUserId(user.getId());
        }
    }
}
