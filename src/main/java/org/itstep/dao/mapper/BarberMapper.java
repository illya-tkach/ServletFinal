package org.itstep.dao.mapper;

import org.itstep.dto.BarberDTO;
import org.itstep.model.entity.BarberLevel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BarberMapper implements ObjectMapper<BarberDTO> {
    @Override
    public BarberDTO extractFromResultSet(ResultSet rs) throws SQLException {
        BarberDTO barber = BarberDTO.newBuilder().setId(rs.getInt("id"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"))
                .setPhotoUrl(rs.getString("photoUrl"))
                .setBarberlLevel(BarberLevel.newBuilder().setLevelName(rs.getString("level_name")).build())
                .build();

        return barber;
    }
}
