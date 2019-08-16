package org.itstep.dao;

import org.itstep.dto.BarberDTO;
import org.itstep.model.entity.Barber;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public interface BarberDao {
    List<BarberDTO> getAllBarbers();
    void findByServiceAndDate(int barberId, int serviceId, Date date, Time time) throws SQLException;
}
