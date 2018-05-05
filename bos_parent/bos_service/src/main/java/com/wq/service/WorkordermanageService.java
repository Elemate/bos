package com.wq.service;

import com.wq.domain.Workordermanage;

import java.util.List;

public interface WorkordermanageService {

    public void save(Workordermanage model);

    List<Workordermanage> findAll();
}
