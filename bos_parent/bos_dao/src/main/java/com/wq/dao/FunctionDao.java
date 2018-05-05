package com.wq.dao;

import com.wq.dao.base.BaseDao;
import com.wq.domain.Function;

import java.util.List;

public interface FunctionDao extends BaseDao<Function> {
    List<Function> findFunctionByUserId(String id);

    List<Function> findMenu();

    List<Function> findMenuByUserId(String id);
}
