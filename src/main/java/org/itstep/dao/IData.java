package org.itstep.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface IData {

    Properties sqlProperties = loadSqlProperties();
    /* Database config */
    String DATABASE_URL = "database.url";
    String DATABASE_USER = "database.user";
    String DATABASE_PASSWORD = "database.password";
    /* IData keys */
    // Barber
    String FIND_All_BARBERS = "select.barber.all";
    String FIND_BY_Ser_AND_DATE = "select.barber.by.service.and.date";
    // Client
    String CLIENT_BY_USERID = "select.client.by.userAccount";
    String ADD_MONEY = "update.client.balance";
    String SAVE_CLIENT = "insert.into.client";
    // Record
    String ALL_AVAILABLE_DATES = "select.record.date.by.status";
    String ALL_TIME_BY_DATE = "select.record.time.by.date";
    String CALCULATE_COST = "select.calculate.service.cost";
    String SET_RESERV_STATUS = "update.record.reserv";
    String ALL_RESERVED_RECORDS = "select.record.all.reserved";
    String WRITE_OFF_MONEY = "update.client.balance.writeOffMoney";
    String SAVE_RECORD = "insert.into.record";
    String ALL_RECORDS = "select.record.all";
    String DELETE_RECORD = "delete.record";
    // Service
    String ALL_SERVICES = "select.service.all";
    // UserAccount
    String FINT_BY_LGN_AND_PSWRD = "select.user.by.nameAndPassword";
    String USER_ROLES_BY_USER_ID = "select.user.role.by.userAccount";
    String SAVE_USER = "insert.into.userAccount";
    String SAVE_USER_ROLE = "insert.into.user.role";

    static Properties loadSqlProperties() {
        try (InputStream inputStream = IData.class.getResourceAsStream("/sql.properties")) {
            Properties sqlProperties = new Properties();
            sqlProperties.load(inputStream);
            return sqlProperties;

        } catch (IOException e) {

        }
        return null;
    }

    static String getSqlElement(String key) {
        return sqlProperties.getProperty(key);
    }


}
