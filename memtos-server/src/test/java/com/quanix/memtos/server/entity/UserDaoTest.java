package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.service.UserService;
import com.quanix.memtos.server.testcase.TransactionalTestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

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
        User user = userService.findById(1L);

        System.out.println(user.getUsername());

        logger.info("get user success");
    }
}
