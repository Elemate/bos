package com.wq.dao;

import com.wq.dao.base.BaseDao;
import com.wq.domain.Region;

import java.util.List;

public interface RegionDao extends BaseDao<Region> {
    List<Region> findRegionByQ(String q);
}
