package org.itstep.service;

import org.itstep.model.entity.Record;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface RecordService {
    List<LocalDate> getAllAvailableDates();
    List<LocalTime> getAllAvailableTimeByDate(LocalDate date);
    Optional<Integer> calculateServiceCost(int barberId, int serviceId);
    void payAndSaveRecord(int barberId, int serviceId, String dateAndTime, int userId) throws SQLException;
    List<Record> findAllReservedRecords();
    void save(int barberId, String date, String time);
    List<Record> findAll();
    void remove(int id);
}
