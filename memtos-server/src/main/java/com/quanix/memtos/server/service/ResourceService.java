package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.OrganizationDao;
import com.quanix.memtos.server.dao.ResourceDao;
import com.quanix.memtos.server.entity.Resource;
import com.quanix.memtos.server.service.base.BaseService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
}
