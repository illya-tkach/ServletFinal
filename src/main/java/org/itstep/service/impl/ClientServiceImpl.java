package org.itstep.service.impl;

import org.itstep.dao.ClientDao;
import org.itstep.dao.impl.JDBCDaoFactory;
import org.itstep.model.entity.Client;
import org.itstep.service.ClientService;

import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    ClientDao clientDao = JDBCDaoFactory.getInstance().createClientDao();

    @Override
    public Optional<Client> findClient(int userAccountId) {
        return clientDao.getClientByUserId(userAccountId);
    }

    @Override
    public void addMoneyToBalance(int money, int userAccountId) {
        clientDao.addMoneyToBalance(money, userAccountId);
    }
}
