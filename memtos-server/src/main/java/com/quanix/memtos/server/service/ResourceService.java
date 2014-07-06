package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.OrganizationDao;
import com.quanix.memtos.server.dao.ResourceDao;
import com.quanix.memtos.server.entity.Resource;
import com.quanix.memtos.server.service.base.BaseService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * created by lihaoquan
 */
@Component
@Transactional
public class ResourceService extends BaseService<Resource,Long> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 自动装载ResourceDao
     * @return
     */
    @Autowired
    private ResourceDao getResourceDao() {
        return (ResourceDao) baseRepository;
    }


    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            Resource resource = findOne(resourceId);
            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }


    /**
     * 获取菜单
     * @param permissions
     * @return
     */
    public List<Resource> findMenus(Set<String> permissions) {
        List<Resource> menus = new ArrayList<Resource>();
        List<Resource> allResources = findAll();
        for(Resource resource : allResources) {
            if(resource.isRootNode()) {
                continue;
            }
            if(resource.getType() != Resource.ResourceType.menu) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }


    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }


    public Resource createResource(final Resource resource) {
        final String sql = "insert into s_resource(name, type, url, permission, parent_id, parent_ids, available) values(?,?,?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++, resource.getName());
                psst.setString(count++, resource.getType().name());
                psst.setString(count++, resource.getUrl());
                psst.setString(count++, resource.getPermission());
                psst.setLong(count++, resource.getParentId());
                psst.setString(count++, resource.getParentIds());
                psst.setBoolean(count++, resource.getAvailable());
                return psst;
            }
        }, keyHolder);
        resource.setId(keyHolder.getKey().longValue());
        return resource;
    }

    public Resource updateResource(Resource resource) {
        final String sql = "update s_resource set name=?, type=?, url=?, permission=?, parent_id=?, parent_ids=?, available=? where id=?";
        jdbcTemplate.update(
                sql,
                resource.getName(), resource.getType().name(), resource.getUrl(), resource.getPermission(), resource.getParentId(), resource.getParentIds(), resource.getAvailable(), resource.getId());
        return resource;
    }

    public void deleteResource(Long resourceId) {
        Resource resource = findOne(resourceId);
        final String deleteSelfSql = "delete from s_resource where id=?";
        jdbcTemplate.update(deleteSelfSql, resourceId);
        final String deleteDescendantsSql = "delete from s_resource where parent_ids like ?";
        jdbcTemplate.update(deleteDescendantsSql, resource.makeSelfAsParentIds() + "%");
    }

}
