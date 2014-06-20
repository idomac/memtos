package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.service.OrganizationService;
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
public class OrganizationDaoTest extends TransactionalTestCase {

    private static Logger logger = LoggerFactory.getLogger(OrganizationDaoTest.class);

    @Autowired
    private OrganizationService organizationService;

    @Test()
    public void testFind() {
        List<Organization> organizations = organizationService.findAll();
        for(Organization organization : organizations) {
            System.out.println(organization.getName());
        }
    }
}
