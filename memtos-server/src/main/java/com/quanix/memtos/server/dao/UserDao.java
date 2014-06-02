package com.quanix.memtos.server.dao;

import com.quanix.memtos.server.entity.User;

import java.util.List;

/**
 * @author : lihaoquan
 */
public interface UserDao {

    public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(Long userId);

    User findOne(Long userId);

    List<User> findAll();

    User findByUsername(String username);
}
