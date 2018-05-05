package com.wq.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wq.domain.User;
import com.wq.utils.BOSUtils;

public class MyInterceptor extends MethodFilterInterceptor {
    private final String LOGIN = "login";

    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

        //获取当前登录用户
        User user = BOSUtils.getLoginUser();
        if (user!=null){
            String actionName = actionInvocation.getProxy().getActionName();
            actionInvocation.getAction();
            String namespace = actionInvocation.getProxy().getNamespace();
            return actionInvocation.invoke();
        }
        return LOGIN;
    }
}
