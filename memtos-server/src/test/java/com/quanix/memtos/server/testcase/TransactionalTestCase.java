package com.quanix.memtos.server.testcase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.sql.DataSource;

/**
 * @author : lihaoquan

 */
public class TransactionalTestCase extends AbstractTransactionalJUnit4SpringContextTests {

    protected DataSource dataSource;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
        this.dataSource = dataSource;
    }
}
