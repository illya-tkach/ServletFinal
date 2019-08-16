package org.itstep.service;

import org.itstep.model.entity.Client;

import java.util.Optional;

public interface ClientService {
    Optional<Client> findClient(int userAccountId);

    void addMoneyToBalance(int money, int userAccountId);
}
