package org.itstep.service;

import org.itstep.dto.BarberDTO;

import java.sql.SQLException;
import java.util.List;

public interface BarberService {
    List<BarberDTO> findAll();
    void findByServiceAndDate(int barberId, int serviceId, String dateAndTime) throws SQLException;
}
