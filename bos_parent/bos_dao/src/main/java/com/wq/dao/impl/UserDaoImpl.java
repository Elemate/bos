package com.wq.dao.impl;

import com.wq.dao.UserDao;
import com.wq.dao.base.impl.BaseDaoImpl;
import com.wq.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public User findUserByNameAndPassword(String username, String password) {
        String hql = "FROM User u where u.username = ? and u.password = ?";

        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);

        if (list!=null && list.size()>0){
            return  list.get(0);
        }

            return null;
        }

    public User findUserByUsername(String username) {
        String hql = "FROM User u where u.username = ?";

        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);

        if (list!=null && list.size()>0){
            return  list.get(0);
        }

        return null;
    }

}


