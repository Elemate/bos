package com.wq.service.impl;

import com.wq.dao.UserDao;
import com.wq.domain.Role;
import com.wq.domain.User;
import com.wq.service.UserService;
import com.wq.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired  //autowried默认按照类型装配的,由spring提供的; 而Resource默认按照名称装配的,在jdk1.6以上版本可用
    public UserDao userDao;

    public User login(User model) {
        return userDao.findUserByNameAndPassword(model.getUsername(), MD5Utils.md5(model.getPassword()));
    }

    public void editPassword(String id, String password) {
        userDao.executeUpdate("user.editPassword", id, MD5Utils.md5(password));
    }

    public void add(User model, String[] roleIds) {
        model.setPassword(MD5Utils.md5(model.getPassword()));
        userDao.save(model);
        if (roleIds!=null&&roleIds.length>0){
            for (String id : roleIds){
                Role role = new Role(id);
                model.getRoles().add(role);
            }
        }
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
