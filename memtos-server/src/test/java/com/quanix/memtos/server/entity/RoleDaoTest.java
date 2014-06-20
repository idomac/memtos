package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.service.RoleService;
import com.quanix.memtos.server.testcase.TransactionalTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * created by lihaoquan
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class RoleDaoTest extends TransactionalTestCase {

    @Autowired
    private RoleService roleService;

    @Test()
    public void testFind() {

        List<Role> roles =  roleService.findAll();
        for(Role role : roles) {
            System.out.println(role.getRole());
        }
    }
}
