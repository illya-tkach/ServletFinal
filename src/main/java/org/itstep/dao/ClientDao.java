package org.itstep.dao;

import org.itstep.model.entity.Client;

import java.util.Optional;

public interface ClientDao {
    Optional<Client> getClientByUserId(int userAccountId);
    void addMoneyToBalance(int money, int userAccountId);
}
