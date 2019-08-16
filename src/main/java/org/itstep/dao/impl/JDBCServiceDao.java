package org.itstep.dao.impl;

import org.itstep.dao.IData;
import org.itstep.dao.ServiceDao;
import org.itstep.dao.mapper.ServiceMapper;
import org.itstep.dto.ServiceDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCServiceDao implements ServiceDao {
    private Connection connection;

    public JDBCServiceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ServiceDTO> getAll() {
        List<ServiceDTO> services = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(IData.getSqlElement(IData.ALL_SERVICES));

            ServiceMapper serviceMapper = new ServiceMapper();
            while (rs.next()) {
                services.add(serviceMapper.extractFromResultSet(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return services;
    }
}
