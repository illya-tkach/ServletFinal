package org.itstep.dao.impl;

import org.apache.log4j.Logger;
import org.itstep.dao.BarberDao;
import org.itstep.dao.IData;
import org.itstep.dao.mapper.BarberMapper;
import org.itstep.dto.BarberDTO;
import org.itstep.model.entity.Barber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCBarberDao implements BarberDao {

    private static final Logger log = Logger.getLogger(JDBCBarberDao.class);

    private Connection connection;

    public JDBCBarberDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<BarberDTO> getAllBarbers() {
        List<BarberDTO> barbers = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(IData.getSqlElement(IData.FIND_All_BARBERS));

            BarberMapper barberMapper = new BarberMapper();
            while (rs.next()) {
                barbers.add(barberMapper.extractFromResultSet(rs));
            }
            log.info("Get Barbers from DB successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return barbers;
    }

    @Override
    public void findByServiceAndDate(int barberId, int serviceId, Date date, Time time) throws SQLException{
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IData.getSqlElement(IData.FIND_BY_Ser_AND_DATE));
            preparedStatement.setInt(1, barberId);
            preparedStatement.setInt(2, serviceId);
            preparedStatement.setDate(3, date);
            preparedStatement.setTime(4, time);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()){
                throw new SQLException();
            }
        } catch (SQLException ex) {
            throw new SQLException("There is no such service or date/time for this barber");
        }
    }
}
