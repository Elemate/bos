package com.wq.service.impl;

import com.wq.dao.RegionDao;
import com.wq.domain.Region;
import com.wq.service.RegionService;
import com.wq.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements RegionService{

    @Autowired
    public RegionDao regionDao;

    public void saveBatch(List<Region> regions) {
        for (Region region: regions){
            regionDao.saveOrUpdate(region);
        }
    }

    public List<Region> findAll() {
       return regionDao.findAll();
    }

    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    public List<Region> findRegionByQ(String q) {

        return regionDao.findRegionByQ(q);
    }
}
