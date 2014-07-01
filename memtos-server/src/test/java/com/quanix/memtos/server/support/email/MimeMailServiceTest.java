package com.quanix.memtos.server.support.email;

import com.quanix.memtos.server.testcase.TransactionalTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author : lihaoquan
 */
@ContextConfiguration(locations = {"/applicationContext.xml","/spring-functions/applicationContext-email.xml"})
public class MimeMailServiceTest extends TransactionalTestCase {

    @Autowired
    private MimeMailService mimeMailService;

    @Test
    public void testSend() {

        mimeMailService.send("rosa");
    }
}
