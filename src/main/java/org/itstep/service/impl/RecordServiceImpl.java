package org.itstep.service.impl;

import org.itstep.dao.BarberDao;
import org.itstep.dao.DaoFactory;
import org.itstep.dao.RecordDao;
import org.itstep.model.entity.Record;
import org.itstep.service.RecordService;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class RecordServiceImpl implements RecordService {

    private static RecordServiceImpl instance;
    RecordDao recordDao = DaoFactory.getInstance().createRecordDao();

    public RecordServiceImpl() {
    }

    public static RecordServiceImpl getInstance() {
        if (instance == null)
            instance = new RecordServiceImpl();
        return instance;
    }

    @Override
    public List<LocalDate> getAllAvailableDates() {
        return recordDao.getAllAvailableDates();
    }

    @Override
    public List<LocalTime> getAllAvailableTimeByDate(LocalDate date) {
        return recordDao.getAllAvailableTimesByDate(date);
    }

    @Override
    public Optional<Integer> calculateServiceCost(int barberId, int serviceId) {
        return recordDao.calculateCost(barberId, serviceId);
    }

    @Override
    public void payAndSaveRecord(int barberId, int serviceId, String dateAndTime, int userId) throws SQLException {
        String[] dateAndTimeString = dateAndTime.split(" ");
        String date = dateAndTimeString[0];
        String time = dateAndTimeString[1];
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(date, dateformatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, timeFormatter);

        recordDao.payAndReserv(barberId, Date.valueOf(localDate), Time.valueOf(localTime), userId, serviceId);

    }

    @Override
    public List<Record> findAllReservedRecords() {
        return recordDao.getAllReservedRecords();
    }

    @Override
    public void save(int barberId, String date, String time) throws java.time.format.DateTimeParseException {

        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(date, dateformatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, timeFormatter);

        recordDao.save(barberId, Date.valueOf(localDate), Time.valueOf(localTime));


    }

    @Override
    public List<Record> findAll() {
        return recordDao.getAllRecords();
    }

    @Override
    public void remove(int id) {
        recordDao.removeById(id);
    }


}
