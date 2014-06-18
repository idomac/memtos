package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.service.UserService;
import com.quanix.memtos.server.testcase.TransactionalTestCase;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.UUID;

/**
 * @author : lihaoquan
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserDaoTest extends TransactionalTestCase {

    private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);


    @Autowired
    private UserService userService;

    @Test
    public void findById() {
        long id = 1;
        User user = userService.findOne(id);

        userService.printModel(user);

        System.out.println(user.getLoginname()+"|"+userService.count());
        logger.info("get user success:"+user.getUsername());

        User fu = userService.findByUsername("admin");

    }
}
