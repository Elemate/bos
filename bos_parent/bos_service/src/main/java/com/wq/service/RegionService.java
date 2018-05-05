package com.wq.service;

import com.wq.domain.Region;
import com.wq.utils.PageBean;

import java.util.List;

public interface RegionService {
    void saveBatch(List<Region> regions);

    List<Region> findAll();

    void pageQuery(PageBean pageBean);

    List<Region> findRegionByQ(String q);
}
