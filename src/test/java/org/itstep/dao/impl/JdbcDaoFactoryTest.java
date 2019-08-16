package org.itstep.dao.impl;

import org.itstep.dao.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class JdbcDaoFactoryTest {

    private DaoFactory daoFactory;

    @Before
    public void setUp() throws Exception {
        daoFactory = DaoFactory.getInstance();
    }

    @Test
    public void testCreateUserAccountDao() {
        UserAccountDao userDao = daoFactory.createUserDao();
        assertNotNull(userDao);
    }

    @Test
    public void testCreateServiceDao() {
        ServiceDao serviceDao = daoFactory.createServiceDao();
        assertNotNull(serviceDao);
    }

    @Test
    public void testCreateRecordDao() {
        RecordDao recordDao = daoFactory.createRecordDao();
        assertNotNull(recordDao);
    }

    @Test
    public void testCreateClientDao() {
        ClientDao clientDao = daoFactory.createClientDao();
        assertNotNull(clientDao);
    }

    @Test
    public void testCreateBarberDao() {
        BarberDao barberDao = daoFactory.createBarberDao();
        assertNotNull(barberDao);
    }
}