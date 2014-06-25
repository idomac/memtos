package com.quanix.memtos.server.remote;

import com.quanix.memtos.core.remote.PermissionContext;
import com.quanix.memtos.core.remote.RemoteServiceInterface;
import com.quanix.memtos.server.service.AuthorizationService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author : lihaoquan
 */
public class RemoteService implements RemoteServiceInterface {

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public Session getSession(String appKey, Serializable sessionId) {
        return sessionDAO.readSession(sessionId);
    }

    @Override
    public Serializable createSession(Session session) {
        return sessionDAO.create(session);
    }

    @Override
    public void updateSession(String appKey, Session session) {
        sessionDAO.update(session);
    }

    @Override
    public void deleteSession(String appKey, Session session) {
        sessionDAO.delete(session);
    }

    /**
     * 远程获取权限信息
     * @param appKey
     * @param username
     * @return
     */
    @Override
    public PermissionContext getPermissions(String appKey, String username) {
        PermissionContext permissionContext = new PermissionContext();
        permissionContext.setPermissions(authorizationService.findPermissions(appKey,username));
        permissionContext.setRoles(authorizationService.findRoles(appKey,username));
        return permissionContext;
    }
}
