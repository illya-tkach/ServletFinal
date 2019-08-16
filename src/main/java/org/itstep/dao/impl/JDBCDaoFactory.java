package org.itstep.dao.impl;

import org.itstep.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserAccountDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public BarberDao createBarberDao() {
        return new JDBCBarberDao(getConnection());
    }

    @Override
    public ServiceDao createServiceDao() {
        return new JDBCServiceDao(getConnection());
    }

    @Override
    public RecordDao createRecordDao() {
        return new JDBCRecordsDao(getConnection());
    }

    @Override
    public ClientDao createClientDao() {
        return new JDBCClientDao(getConnection());
    }


    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
