package org.itstep.model.entity.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateAndTimeValidator {
    /**
     * Check whether form parameter is a valid DateAndTime
     *
     * @param dateAndTime form parameter
     * @return true if user input valid dateAndTime, false otherwise
     */
    public boolean isValidDateAndTime(String dateAndTime) {
        try {
            String[] dateAndTimeString = dateAndTime.split(" ");
            String date = dateAndTimeString[0];
            String time = dateAndTimeString[1];
            DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate.parse(date, dateformatter);
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime.parse(time, timeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Check whether form parameter is a valid date
     *
     * @param date form parameter
     * @return true if user input valid date, false otherwise
     */
    public boolean isValidDate(String date) {
        try {
            DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate.parse(date, dateformatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Check whether form parameter is a valid time
     *
     * @param time form parameter
     * @return true if user input valid time, false otherwise
     */
    public boolean isValidTime(String time) {
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime.parse(time, timeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
