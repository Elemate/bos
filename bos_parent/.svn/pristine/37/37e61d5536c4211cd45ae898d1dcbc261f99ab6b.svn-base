package com.wq.web.action;

import com.wq.domain.Workordermanage;
import com.wq.service.WorkordermanageService;
import com.wq.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {

    @Autowired
    private WorkordermanageService workordermanageService;

    public String save(){
        workordermanageService.save(model);
        return NONE;
    }

    public String pagequery(){
        List<Workordermanage> list = workordermanageService.findAll();
        java2Json(list, new String[]{});
        return NONE;
    }
}
