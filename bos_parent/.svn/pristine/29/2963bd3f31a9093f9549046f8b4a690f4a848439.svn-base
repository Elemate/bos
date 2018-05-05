package com.wq.dao.base;

import com.wq.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

    void save(T entity);
    void delete(T entity);
    void update(T entity);
    T findById(Serializable id);
    List<T> findAll();
    void executeUpdate(String queryName, Object... objects);
    void pageQuery(PageBean pageBean);
    void saveOrUpdate(Object object);
    List<T> findByCriteria(DetachedCriteria criteria);
}
