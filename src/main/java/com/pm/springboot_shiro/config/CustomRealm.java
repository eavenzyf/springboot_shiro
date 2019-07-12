package com.pm.springboot_shiro.config;
import com.pm.springboot_shiro.dao.ManagerMapper;
import com.pm.springboot_shiro.pojo.Manager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname CustomRealm
 * @Description TODO
 * @Date 2019/7/12 8:35
 * @Created by Eaven
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        Manager manager = managerMapper.findManagerByName(username);
        if(manager == null){
            throw new AuthenticationException("用户名不存在！");
        }
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(token.getPrincipal(),manager.getPassword(),this.getName());
        super.clearCachedAuthenticationInfo(authcInfo.getPrincipals());
        SecurityUtils.getSubject().getSession().setAttribute("manager",manager);
        return authcInfo ;
    }
}
