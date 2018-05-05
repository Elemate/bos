package com.wq.dao.impl;

import com.wq.dao.RegionDao;
import com.wq.dao.base.impl.BaseDaoImpl;
import com.wq.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {
    public List<Region> findRegionByQ(String q) {
        String hql = "from Region r where r.shortcode like ? or r.citycode like ? or r.province like ? or r.city like ?" +
                " or r.district like ?";
        List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql,"%;"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
        return list;
    }
}
