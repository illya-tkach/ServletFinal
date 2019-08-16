package org.itstep.dao.mapper;

import org.itstep.model.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements ObjectMapper<Client> {
    @Override
    public Client extractFromResultSet(ResultSet rs) throws SQLException {
        Client client = Client.newBuilder().setId(rs.getInt("id"))
                .setBalance(rs.getInt("balance"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"))
                .setEmail(rs.getString("email"))
                .build();

        return client;
    }
}
