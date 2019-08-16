package org.itstep.dao.mapper;

import org.itstep.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordMapper implements ObjectMapper<Record> {
    @Override
    public Record extractFromResultSet(ResultSet rs) throws SQLException {
        Record record = Record.newBuilder().setStatus(ServiceStatus.valueOf(rs.getString("status")))
                .setDate(rs.getDate("date").toLocalDate())
                .setId(rs.getInt("id"))
                .setTime(rs.getTime("time").toLocalTime())
                .setBarber(Barber.newBuilder().setFirstName(rs.getString("barberFirstN"))
                        .setLastName(rs.getString("barberLastN"))
                        .build())
                .setClient(Client.newBuilder().setEmail(rs.getString("email"))
                        .setFirstName(rs.getString("clientFirstN"))
                        .setLastName(rs.getString("clientLastN"))
                        .build())
                .setService(Service.newBuilder().setName(rs.getString("service_name")).build())
                .build();

        return record;
    }
}
