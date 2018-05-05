package com.wq.dao;

import com.wq.dao.base.BaseDao;
import com.wq.domain.User;

public interface UserDao extends BaseDao<User> {

    //根据用户名和密码查看用户是否存在
    public User findUserByNameAndPassword(String username, String password);


    User findUserByUsername(String username);

    //修改密码
}