package com.wq.service;

import com.wq.domain.Decidedzone;
import com.wq.domain.Subarea;
import com.wq.utils.PageBean;

import java.util.List;

public interface DecidedzoneService {
    void save(Decidedzone model,String[] subareaId);

    void pageQuery(PageBean pageBean);

    List<Subarea> findListByDecidezoneId(String id);
}
