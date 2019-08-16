package org.itstep.dao;

import org.itstep.model.entity.Record;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface RecordDao {
    List<LocalTime> getAllAvailableTimesByDate(LocalDate date);

    List<LocalDate> getAllAvailableDates();

    Optional<Integer> calculateCost(int barberId, int serviceId);

    void payAndReserv(int barberId, Date date, Time time, int userId, int serviceId) throws SQLException;

    List<Record> getAllReservedRecords();

    void save(int barberId, Date date, Time time);

    List<Record> getAllRecords();

    void removeById(int id);
}