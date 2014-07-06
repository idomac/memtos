package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.RoleDao;
import com.quanix.memtos.server.entity.Role;
import com.quanix.memtos.server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * created by lihaoquan
 */
@Component
@Transactional
public class RoleService extends BaseService<Role,Long> {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 自动装载RoleDao
     * @return
     */
    @Autowired
    private RoleDao getRoleDao() {
        return (RoleDao) baseRepository;
    }


    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                //resourceIds.addAll(role.getResourceIds());
                String[] arr = role.getResourceIds().split(",");
                for(String a : arr) {
                    resourceIds.add(Long.parseLong(a));
                }
            }
        }
        return resourceService.findPermissions(resourceIds);
    }


    public Role createRole(final Role role) {
        final String sql = "insert into s_role(role, description, resource_ids, available) values(?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++, role.getRole());
                psst.setString(count++, role.getDescription());
                psst.setString(count++, role.getResourceIds());
                psst.setBoolean(count++, role.getAvailable());
                return psst;
            }
        }, keyHolder);
        role.setId(keyHolder.getKey().longValue());
        return role;
    }

    public Role updateRole(Role role) {
        final String sql = "update s_role set role=?, description=?, resource_ids=?, available=? where id=?";
        jdbcTemplate.update(
                sql,
                role.getRole(), role.getDescription(), role.getResourceIds(), role.getAvailable(), role.getId());
        return role;
    }

    public void deleteRole(Long roleId) {
        final String sql = "delete from s_role where id=?";
        jdbcTemplate.update(sql, roleId);
    }
}
