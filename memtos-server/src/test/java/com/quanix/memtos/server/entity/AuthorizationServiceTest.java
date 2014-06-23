package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.service.AuthorizationService;
import com.quanix.memtos.server.testcase.TransactionalTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * created by lihaoquan
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AuthorizationServiceTest extends TransactionalTestCase {

    @Autowired
    private AuthorizationService authorizationService;

    @Test
    public void testFind() {
        List<Authorization> authorizations = authorizationService.findAll();
        for(Authorization authorization : authorizations) {
            //System.out.println(authorization.getUserId());
        }
    }
}
