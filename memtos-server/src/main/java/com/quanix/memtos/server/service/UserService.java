package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.UserDao;
import com.quanix.memtos.server.entity.User;
import com.quanix.memtos.server.helper.PasswordHelper;
import com.quanix.memtos.server.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

/**
 * @author : lihaoquan
 *
 * 用户服务类
 */
@Component
@Transactional
public class UserService extends BaseService<User,Long> {


    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordHelper passwordHelper;

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


    public Set<String> findPermissions(String username){
        User user =  getUserDao().findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findPermissions(user.getRoleIdsList().toArray(new Long[0]));
    }

    public void changePassword(Long userId,String newPassword) {
        User user = baseRepository.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        baseRepository.save(user);//更新
    }


    /**
     * 继承父类的保存方法,对密码进行处理
     * @param user
     * @return
     */
    @Override
    public User save(User user) {
        passwordHelper.encryptPassword(user);
        return super.save(user);
    }
}
