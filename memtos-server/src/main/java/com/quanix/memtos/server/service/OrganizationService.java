package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.OrganizationDao;
import com.quanix.memtos.server.entity.Organization;
import com.quanix.memtos.server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by lihaoquan
 */
@Component
@Transactional
public class OrganizationService extends BaseService<Organization,Long> {

    /**
     * 自动装载OrganizationDao
     * @return
     */
    @Autowired
    private OrganizationDao getOrganizationDao() {
        return (OrganizationDao) baseRepository;
    }
}
