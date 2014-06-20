package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.OrganizationDao;
import com.quanix.memtos.server.dao.ResourceDao;
import com.quanix.memtos.server.entity.Resource;
import com.quanix.memtos.server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

}
