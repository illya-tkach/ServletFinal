package org.itstep.service.impl;

import org.itstep.dao.DaoFactory;
import org.itstep.dao.ServiceDao;
import org.itstep.dto.ServiceDTO;
import org.itstep.service.ServiceService;

import java.util.List;

public class ServiceServiceImpl implements ServiceService {

    ServiceDao serviceDao = DaoFactory.getInstance().createServiceDao();

    @Override
    public List<ServiceDTO> findAll() {
        return serviceDao.getAll();
    }
}
