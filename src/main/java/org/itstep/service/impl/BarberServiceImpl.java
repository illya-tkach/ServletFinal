package org.itstep.service.impl;

import org.itstep.dao.BarberDao;
import org.itstep.dao.DaoFactory;
import org.itstep.dto.BarberDTO;
import org.itstep.model.entity.Barber;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BarberServiceImpl implements org.itstep.service.BarberService {

    private static BarberServiceImpl instance;

    BarberDao barberDao = DaoFactory.getInstance().createBarberDao();

    private BarberServiceImpl() {
    }

    public static BarberServiceImpl getInstance() {
        if (instance == null)
            instance = new BarberServiceImpl();
        return instance;
    }

    @Override
    public List<BarberDTO> findAll() {
        return barberDao.getAllBarbers();
    }

    @Override
    public void findByServiceAndDate(int barberId, int serviceId, String dateAndTime) throws SQLException {
        String[] dateAndTimeString = dateAndTime.split(" ");
        String date = dateAndTimeString[0];
        String time = dateAndTimeString[1];
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(date, dateformatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, timeFormatter);

        barberDao.findByServiceAndDate(barberId, serviceId, Date.valueOf(localDate), Time.valueOf(localTime));
    }


}
