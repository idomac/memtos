package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.UserDao;
import com.quanix.memtos.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : lihaoquan
 */
@Component
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;


    public User findById(Long id) {
        return userDao.findById(id);
    }
}
