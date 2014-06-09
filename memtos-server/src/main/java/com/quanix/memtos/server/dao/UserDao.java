package com.quanix.memtos.server.dao;

import com.quanix.memtos.server.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author : lihaoquan
 */
public interface UserDao extends CrudRepository<User,Long> {

    public User findById(Long id);
}
