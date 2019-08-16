package org.itstep.dao.impl;

import org.itstep.dao.ClientDao;
import org.itstep.dao.IData;
import org.itstep.dao.mapper.ClientMapper;
import org.itstep.model.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JDBCClientDao implements ClientDao {
    private Connection connection;

    public JDBCClientDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Client> getClientByUserId(int userAccountId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.CLIENT_BY_USERID));
            preparedStatement.setInt(1, userAccountId);
            ResultSet rs = preparedStatement.executeQuery();

            ClientMapper clientMapper = new ClientMapper();
            while (rs.next()) {
                return Optional.of(clientMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void addMoneyToBalance(int money, int userAccountId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.ADD_MONEY));
            preparedStatement.setInt(1, money);
            preparedStatement.setInt(2, userAccountId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
