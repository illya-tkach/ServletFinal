package org.itstep.service.impl;


import org.itstep.dao.DaoFactory;
import org.itstep.dao.UserAccountDao;
import org.itstep.model.entity.Client;
import org.itstep.model.entity.UserAccount;
import org.itstep.service.UserService;

import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    UserAccountDao userDao = DaoFactory.getInstance().createUserDao();

    @Override
    public UserAccount save(Client client) throws SQLException {

        return userDao.saveUser(client);
    }

    @Override
    public Optional<UserAccount> findUserByLgnAndPswrd(String userName, String password) {
        Optional<UserAccount> userAccount = userDao.getUserByLgnAndPswrd(userName, password);

        if (userAccount.isPresent())
            userAccount.get().setRoles(userDao.getUserRolesByUserId(userAccount.get().getId()));

        return userAccount;
    }

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;


}
