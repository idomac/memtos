package com.quanix.memtos.server.dao;

import com.quanix.memtos.server.entity.Authorization;

import java.util.List;

/**
 * created by lihaoquan
 */
public interface AuthorizationDao {

    public Authorization createAuthorization(Authorization authorization);
    public Authorization updateAuthorization(Authorization authorization);
    public void deleteAuthorization(Long authorizationId);

    public Authorization findOne(Long authorizationId);
    public List<Authorization> findAll();

    public Authorization findByAppUser(Long appId, Long userId);
}
