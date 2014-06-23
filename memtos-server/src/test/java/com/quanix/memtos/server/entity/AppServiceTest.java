package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.service.AppService;
import com.quanix.memtos.server.testcase.TransactionalTestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * created by lihaoquan
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AppServiceTest extends TransactionalTestCase {

    private static Logger logger = LoggerFactory.getLogger(AppServiceTest.class);

    @Autowired
    private AppService appService;

    @Test
    public void testFind() {

        List<App> appList = appService.findAll();

        for(App app : appList) {
            System.out.println(app.getName());
        }

        Long app = appService.findAppIdByAppKey("645ba613-370a-43a8-a8e0-993e7a590cf0");
        System.out.println("app id : "+app);
    }

}
