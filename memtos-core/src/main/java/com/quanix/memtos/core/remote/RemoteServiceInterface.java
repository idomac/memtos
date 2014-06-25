package com.quanix.memtos.core.remote;

import org.apache.shiro.session.Session;

import java.io.Serializable;

/**
 * @author : lihaoquan
 *
 * 远程服务接口
 */
public interface RemoteServiceInterface {

    public Session getSession(String appKey,Serializable sessionId);

    public Serializable createSession(Session session);

    public void updateSession(String appKey,Session session);

    public void deleteSession(String appKey, Session session);

    public PermissionContext getPermissions(String appKey,String username);
}
