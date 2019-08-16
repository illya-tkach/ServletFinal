package org.itstep.controller.command.record;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.itstep.controller.command.Command;
import org.itstep.service.RecordService;
import org.itstep.service.impl.RecordServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimeCommand implements Command {

    private static final Logger log = Logger.getLogger(TimeCommand.class);

    RecordService recordService = RecordServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String date = request.getParameter("date");

        System.out.println(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate localDate = LocalDate.parse(date, formatter);

        try {
            response.setContentType("application/json");
            List<LocalTime> times = recordService.getAllAvailableTimeByDate(localDate);
            String json = new ObjectMapper().writeValueAsString(times);
            response.getWriter().write(json);
            return "response:";
        } catch (IOException e) {
            log.info("Exception when retrieve data");
            e.printStackTrace();
        }

        request.setAttribute("error", "Error in TimeCommand");
        return "/WEB-INF/error.jsp";
    }
}
