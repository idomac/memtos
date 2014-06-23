package com.quanix.memtos.server.service.impl;

import com.quanix.memtos.server.dao.AuthorizationDao;
import com.quanix.memtos.server.entity.Authorization;
import com.quanix.memtos.server.service.AppService;
import com.quanix.memtos.server.service.AuthorizationService;
import com.quanix.memtos.server.service.RoleService;
import com.quanix.memtos.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * created by lihaoquan
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AuthorizationDao authorizationDao;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AppService appService;

    @Override
    public Authorization createAuthorization(Authorization authorization) {
        return null;
    }

    @Override
    public Authorization updateAuthorization(Authorization authorization) {
        return null;
    }

    @Override
    public void deleteAuthorization(Long authorizationId) {

    }

    @Override
    public Authorization findOne(Long authorizationId) {
        return null;
    }

    @Override
    public List<Authorization> findAll() {
        return authorizationDao.findAll();
    }

    @Override
    public Set<String> findRoles(String appKey, String username) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String appKey, String username) {
        return null;
    }
}
