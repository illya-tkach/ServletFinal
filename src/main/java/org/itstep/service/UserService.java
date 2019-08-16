package org.itstep.service;


import org.itstep.model.entity.Client;
import org.itstep.model.entity.UserAccount;

import java.sql.SQLException;
import java.util.Optional;

public interface UserService {
    UserAccount save(Client client) throws SQLException;

    Optional<UserAccount> findUserByLgnAndPswrd(String username, String password);
}
