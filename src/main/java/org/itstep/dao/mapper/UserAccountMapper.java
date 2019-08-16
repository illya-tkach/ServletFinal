package org.itstep.dao.mapper;

import org.itstep.model.entity.UserAccount;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserAccountMapper implements ObjectMapper<UserAccount> {


    @Override
    public UserAccount extractFromResultSet(ResultSet rs) throws SQLException {
        UserAccount userAccount = UserAccount.newBuilder().setUserId(rs.getInt("id"))
                .setUserName(rs.getString("user_name"))
                .setPassword(rs.getString("password"))
                .build();
        return userAccount;
    }

}
