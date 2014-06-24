package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.service.AuthorizationService;
import com.quanix.memtos.server.testcase.TransactionalTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Set;

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
            System.out.println(authorization.getUserId());
        }
    }

    @Test
    public void testFindOne() {
        Authorization authorization =  authorizationService.findOne(2L);
        System.out.print(authorization.getRoleIdsStr());
    }

    @Test
    public void testFindRoles() {
        Set<String> roles = authorizationService.findRoles("645ba616-370a-43a8-a8e0-993e7a590cf0", "admin");
        System.out.println(roles.size());
    }

    @Test
    public void testFindPermissions() {
        Set<String> permissions = authorizationService.findPermissions("645ba616-370a-43a8-a8e0-993e7a590cf0", "admin");
        System.out.println(permissions.size());
    }
}
