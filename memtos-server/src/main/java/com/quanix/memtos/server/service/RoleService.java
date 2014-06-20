package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.RoleDao;
import com.quanix.memtos.server.entity.Role;
import com.quanix.memtos.server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    Set<String> findRoles(Long... roleIds) {
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
}
