package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.AppDao;
import com.quanix.memtos.server.entity.App;
import com.quanix.memtos.server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by lihaoquan
 */
@Component
@Transactional
public class AppService extends BaseService<App,Long> {
    /**
     * 自动装载RoleDao
     * @return
     */
    @Autowired
    private AppDao getAppDao() {
        return (AppDao) baseRepository;
    }

    public Long findAppIdByAppKey(String app_key) {
        return getAppDao().findAppIdByAppKey(app_key);
    }
}
