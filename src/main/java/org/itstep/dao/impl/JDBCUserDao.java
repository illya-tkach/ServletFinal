package org.itstep.dao.impl;

import org.itstep.controller.config.SecurityConfig;
import org.itstep.dao.IData;
import org.itstep.dao.UserAccountDao;
import org.itstep.dao.mapper.UserAccountMapper;
import org.itstep.dao.mapper.UserAccountRoleMapper;
import org.itstep.model.entity.Client;
import org.itstep.model.entity.UserAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class JDBCUserDao implements UserAccountDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<UserAccount> getUserByLgnAndPswrd(String username, String password) {
        UserAccount userAccount;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.FINT_BY_LGN_AND_PSWRD));
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            UserAccountMapper userMapper = new UserAccountMapper();

            while (rs.next()) {
                userAccount = userMapper.extractFromResultSet(rs);
                if (userAccount != null) return Optional.of(userAccount);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<String> getUserRolesByUserId(int userId) {
        List<String> userRoles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.USER_ROLES_BY_USER_ID));
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            UserAccountRoleMapper roleMapper = new UserAccountRoleMapper();

            while (rs.next()) {
                userRoles.add(roleMapper.extractFromResultSet(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userRoles;
    }

    @Override
    public UserAccount saveUser(Client client) throws SQLException {
        UserAccount userAccount = client.getUserAccount();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.SAVE_USER), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, userAccount.getUserName());
        preparedStatement.setString(2, userAccount.getPassword());

        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Duplicate username or password");
        }


        int userId;
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        List<String> roles = saveUserRole(userId);

        try {
            saveAsClient(client, userId);
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Duplicate email");
        }

        connection.commit();
        return UserAccount.newBuilder().setUserId(userId)
                .setUserName(userAccount.getUserName())
                .setPassword(userAccount.getPassword())
                .setRoles(roles)
                .build();
    }

    public List<String> saveUserRole(int userId) throws SQLException {
        List<String> roles = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.SAVE_USER_ROLE));
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, 1);
        preparedStatement.executeUpdate();
        roles.add(SecurityConfig.ROLE_CLIENT);
        return roles;
    }

    void saveAsClient(Client client, int userAccountId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.SAVE_CLIENT));
        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setString(3, client.getEmail());
        preparedStatement.setInt(4, userAccountId);
        preparedStatement.executeUpdate();
    }
}
