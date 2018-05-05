package com.wq.web.action;

import com.wq.domain.User;
import com.wq.service.UserService;
import com.wq.utils.BOSUtils;
import com.wq.utils.MD5Utils;
import com.wq.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")     // 这个是原型,在访问时创建这个Action对象.如果是单例的,在服务器启动时创建这个Action对象
public class UserAction extends BaseAction<User> {

    @Resource
    private UserService userService;

    //接收页面验证码
    private String checkCode;

    //用户登录
    public String login(){

        String checkCode_new = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //比对验证码是否正确
        if (StringUtils.isNotBlank(checkCode_new) && checkCode_new.equals(checkCode)){
           //利用shiro框架提供的方式进行登录认证
            //获得Subject,等价于User
            Subject subject = SecurityUtils.getSubject();       //获得当前登录用户信息，状态是未认证
            //用户名密码令牌
            AuthenticationToken authenticationToken = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
            try {
                //这一步调用啥realm，进行认证
                subject.login(authenticationToken);
            } catch (Exception e){
                e.printStackTrace();
                this.addActionError("登陆用户名或者密码错误");
                return LOGIN;
            }
            User user = (User) subject.getPrincipal();
            ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
            return HOME;
        } else
            this.addActionError("验证码错误");
            return LOGIN;

    }
    //用户登录
   /* public String login_bak(){

        String checkCode_new = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //比对验证码是否正确
        if (StringUtils.isNotBlank(checkCode_new) && checkCode_new.equals(checkCode)){
           User user = userService.login(model);
           //判断用户是否存在
            if (user!=null){
                ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
                return HOME;
            } else {
                this.addActionError("用户名或密码错误");
                return LOGIN;
            }

        } else
            this.addActionError("验证码错误");
            return LOGIN;

    }*/

    //注销登陆
    public String invalidate(){
        User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        if (loginUser!=null){
            ServletActionContext.getRequest().getSession().invalidate();
        }
        return LOGIN;
    }

    //修改密码
    public String editPassword() throws IOException {
        String f = "1";
        //获取当前登录用户
        User loginUser = BOSUtils.getLoginUser();
        try {
            userService.editPassword(loginUser.getId(), loginUser.getPassword());

        } catch (Exception e) {
            e.printStackTrace();
            f = "0";
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(f);
        return NONE;
    }

    private String[] roleIds;

    //新增用户
    public String add(){
        userService.add(model,roleIds);
        return LIST;
    }

    public String listAjax(){

        List<User> users = userService.findAll();
        java2Json(users, new String[]{"roles"});

        return NONE;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public void setCheckCode(String checkCode){

        this.checkCode = checkCode;
    }

}
