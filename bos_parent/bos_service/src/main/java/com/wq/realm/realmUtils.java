package com.wq.realm;


import com.wq.dao.FunctionDao;
import com.wq.dao.UserDao;
import com.wq.domain.Function;
import com.wq.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class realmUtils extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;
    @Autowired
    private FunctionDao functionDao;
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //基于url使用过滤器拦截方式控制权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //TODO 后期根据当前登陆用户，查询数据库获得相对应的权限
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Function> functions = null;
        if (user.getUsername().equals("admin")){

            //超级用户,查询所有的权限
            DetachedCriteria criteria = DetachedCriteria.forClass(Function.class);
            functions = functionDao.findByCriteria(criteria);
        } else {

            //根据用户id查询用户所具有的权限
           functions = functionDao.findFunctionByUserId(user.getId());

        }
        for (Function function : functions) {
            info.addStringPermission(function.getCode()
            );
        }
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //根据用户名去查询数据库的密码，然后再比对跟页面输入的密码是否一致，就是这么脑残
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userDao.findUserByUsername(username);
        if (user == null){
            return null;
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),this.getName());
        return info;
    }
}
