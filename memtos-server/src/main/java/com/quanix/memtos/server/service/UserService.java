package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.UserDao;
import com.quanix.memtos.server.entity.User;
import com.quanix.memtos.server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : lihaoquan
 *
 * 用户服务类
 */
@Component
@Transactional
public class UserService extends BaseService<User,Long> {


    /**
     * 自动装载UserDao
     * @return
     */
    @Autowired
    private UserDao getUserDao() {
        return (UserDao) baseRepository;
    }


    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return getUserDao().findByUsername(username);
    }

}
