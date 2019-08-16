package org.itstep.dao.impl;

import org.itstep.dao.IData;
import org.itstep.dao.RecordDao;
import org.itstep.dao.mapper.RecordMapper;
import org.itstep.model.entity.Record;
import org.itstep.model.entity.ServiceStatus;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCRecordsDao implements RecordDao {
    private Connection connection;

    public JDBCRecordsDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<LocalTime> getAllAvailableTimesByDate(LocalDate date) {
        List<LocalTime> times = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.ALL_TIME_BY_DATE));
            preparedStatement.setString(1, ServiceStatus.NOT_RESERVED.name());
            preparedStatement.setDate(2, Date.valueOf(date));
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Time time = rs.getTime("time");
                System.out.println(time.getTime());

                times.add(rs.getTime("time").toLocalTime());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return times;
    }

    @Override
    public List<LocalDate> getAllAvailableDates() {
        List<LocalDate> dates = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.ALL_AVAILABLE_DATES));
            preparedStatement.setString(1, ServiceStatus.NOT_RESERVED.name());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                dates.add(rs.getDate("date").toLocalDate());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return dates;
    }

    @Override
    public Optional<Integer> calculateCost(int barberId, int serviceId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.CALCULATE_COST));
            preparedStatement.setInt(1, barberId);
            preparedStatement.setInt(2, serviceId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return Optional.of(rs.getInt("cost"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void payAndReserv(int barberId, Date date, Time time, int userId, int serviceId) throws SQLException {

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.SET_RESERV_STATUS));
            preparedStatement.setInt(1, serviceId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, barberId);
            preparedStatement.setDate(4, date);
            preparedStatement.setTime(5, time);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("This record was already reserved");
        }

        try {
            PreparedStatement preparedStWtiteOffMoney = connection.prepareStatement(IData.getSqlElement(IData.WRITE_OFF_MONEY));
            preparedStWtiteOffMoney.setInt(1, barberId);
            preparedStWtiteOffMoney.setInt(2, serviceId);
            preparedStWtiteOffMoney.setInt(3, userId);
            int affectedRows = preparedStWtiteOffMoney.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException();
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Not enough money in your account");
        }
    }

    @Override
    public List<Record> getAllReservedRecords() {
        List<Record> records = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(IData.getSqlElement(IData.ALL_RESERVED_RECORDS));
            RecordMapper recordMapper = new RecordMapper();
            while (rs.next()) {
                records.add(recordMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return records;
    }

    @Override
    public void save(int barberId, Date date, Time time) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.SAVE_RECORD));
            preparedStatement.setInt(1, barberId);
            preparedStatement.setDate(2, date);
            preparedStatement.setTime(3, time);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Record> getAllRecords() {
        List<Record> records = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(IData.getSqlElement(IData.ALL_RECORDS));
            RecordMapper recordMapper = new RecordMapper();
            while (rs.next()) {
                records.add(recordMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return records;
    }

    @Override
    public void removeById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.DELETE_RECORD));
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
