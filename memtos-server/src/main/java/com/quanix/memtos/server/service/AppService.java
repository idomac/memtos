package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.AppDao;
import com.quanix.memtos.server.entity.App;
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

/**
 * created by lihaoquan
 */
@Component
@Transactional
public class AppService extends BaseService<App,Long> {

    @Autowired
    private JdbcTemplate jdbcTemplate;


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

    public App createApp(final App app) {
        final String sql = "insert into s_app(name, app_key, app_secret) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++, app.getName());
                psst.setString(count++, app.getApp_key());
                psst.setString(count++, app.getApp_secret());
                return psst;
            }
        }, keyHolder);
        app.setId(keyHolder.getKey().longValue());
        return app;
    }

    public App updateApp(App app) {
        final String sql = "update s_app set name=?, app_key=?, app_secret=? where id=?";
        jdbcTemplate.update(
                sql,
                app.getName(), app.getApp_key(), app.getApp_secret(), app.getId());
        return app;
    }

    public void deleteApp(Long appId) {
        final String sql = "delete from s_app where id=?";
        jdbcTemplate.update(sql, appId);
    }
}
