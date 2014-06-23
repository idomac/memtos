package com.quanix.memtos.server.dao;

import com.quanix.memtos.server.dao.repository.BaseRepository;
import com.quanix.memtos.server.entity.App;
import org.springframework.data.jpa.repository.Query;

/**
 * created by lihaoquan
 */
public interface AppDao extends BaseRepository<App,Long> {

    @Query("select app.id from App app where app.app_key=?1")
    public Long findAppIdByAppKey(String app_key);
}
