package com.quanix.memtos.server.dao.impl;

import com.quanix.memtos.server.dao.AuthorizationDao;
import com.quanix.memtos.server.entity.Authorization;
import com.quanix.memtos.server.service.RoleService;
import com.quanix.memtos.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by lihaoquan
 */
@Repository(value = "authorizationDao")
public class AuthorizationDaoImpl implements AuthorizationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


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
        final String sql = "select id,user_id,app_id,role_ids as roleIdsStr from s_user_app_roles";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(Authorization.class));
        //return null;
    }

    @Override
    public Authorization findByAppUser(Long appId, Long userId) {
        return null;
    }
}
