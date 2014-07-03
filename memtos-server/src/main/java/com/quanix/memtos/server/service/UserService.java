package com.quanix.memtos.server.service;

import com.quanix.memtos.server.dao.UserDao;
import com.quanix.memtos.server.entity.User;
import com.quanix.memtos.server.helper.PasswordHelper;
import com.quanix.memtos.server.service.base.BaseService;
import com.quanix.memtos.server.support.jms.NotifyMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private NotifyMessageProducer notifyProducer;

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
        // 发送JMS消息
        if (notifyProducer != null) {
            sendNotifyMessage(user);
        }
        return super.save(user);
    }


    /**
     * 发送用户变更消息.
     *
     * 同时发送只有一个消费者的Queue消息与发布订阅模式有多个消费者的Topic消息.
     */
    private void sendNotifyMessage(User user) {
        try {
            //notifyProducer.sendQueue(user);
            notifyProducer.sendTopic(user);
        } catch (Exception e) {
            logger.error("消息发送失败", e);
        }
    }

    @Autowired(required = false)
    public void setNotifyProducer(NotifyMessageProducer notifyProducer) {
        this.notifyProducer = notifyProducer;
    }
}
