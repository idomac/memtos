package com.quanix.memtos.server.remote;

import com.quanix.memtos.core.remote.PermissionContext;
import com.quanix.memtos.core.remote.RemoteServiceInterface;
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

    @Override
    public PermissionContext getPermissions(String appKey, String username) {
        PermissionContext permissionContext = new PermissionContext();
        permissionContext.setPermissions(null);
        permissionContext.setRoles(null);
        return permissionContext;
    }
}
