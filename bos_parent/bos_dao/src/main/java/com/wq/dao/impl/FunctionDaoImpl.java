package com.wq.dao.impl;

import com.wq.dao.FunctionDao;
import com.wq.dao.base.impl.BaseDaoImpl;
import com.wq.domain.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao {


    //查找所有权限以树形结构显示
    @Override
    public List<Function> findAll() {
        String hql = "from Function f where f.parentFunction is null";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);

        return list;
    }

    //根据用户id查询用户具有的权限
    public List<Function> findFunctionByUserId(String id) {
        String sql = "select distinct f from Function f left join f.roles r left join r.users u where u.id = ?";
        List<Function> funtions  = (List<Function>) this.getHibernateTemplate().find(sql,id);
        return funtions;
    }

    public List<Function> findMenu() {
        String hql = "from Function f where f.generatemenu = '1' order by f.zindex desc";
        List<Function> functions = (List<Function>) this.getHibernateTemplate().find(hql);
        return functions;
    }

    public List<Function> findMenuByUserId(String id) {
       String hql = "select distinct f from Function f left join f.roles r left join r.users u where u.id= ? and f.generatemenu = '1' order by f.zindex desc";

        List<Function> functions = (List<Function>) this.getHibernateTemplate().find(hql, id);
        return functions;
    }
}
