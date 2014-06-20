package com.quanix.memtos.server.entity;

import com.quanix.memtos.server.service.ResourceService;
import com.quanix.memtos.server.testcase.TransactionalTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * created by lihaoquan
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class ResourceDaoTest extends TransactionalTestCase {

    @Autowired
    private ResourceService resourceService;


    @Test()
    public void testFind() {
        List<Resource> resources = resourceService.findAll();
        for(Resource resource : resources) {
            System.out.println(resource.getName()+"----"+resource.getType());
        }
    }
}
