package org.itstep.dao.mapper;

import org.itstep.dto.ServiceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMapper implements ObjectMapper<ServiceDTO> {
    @Override
    public ServiceDTO extractFromResultSet(ResultSet rs) throws SQLException {
        ServiceDTO service = ServiceDTO.newBuilder().setId(rs.getInt("id"))
                .setName(rs.getString("service_name"))
                .setCost(rs.getInt("cost"))
                .build();

        return service;
    }
}
