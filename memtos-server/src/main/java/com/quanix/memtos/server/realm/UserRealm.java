package com.quanix.memtos.server.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author : lihaoquan
 *
 * 自定义的userRealm
 */
public class UserRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "myRelam";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        System.out.println("2. ================doGetAuthorizationInfo");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("1 .================doGetAuthenticationInfo");

        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getPrincipal());

        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
