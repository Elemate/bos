package com.wq.utils;

/*
    获取Session以及当前登录用户
*/

import com.wq.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class BOSUtils {

    public static HttpSession getSession(){
        return ServletActionContext.getRequest().getSession();
    }

    public static User getLoginUser(){
        return (User) getSession().getAttribute("loginUser");
    }
}
