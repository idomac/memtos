package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.RoleDao;
import com.quanix.memtos.server.entity.Role;
import com.quanix.memtos.server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by lihaoquan
 */
@Component
@Transactional
public class RoleService extends BaseService<Role,Long> {

    /**
     * 自动装载RoleDao
     * @return
     */
    @Autowired
    private RoleDao getRoleDao() {
        return (RoleDao) baseRepository;
    }
}
