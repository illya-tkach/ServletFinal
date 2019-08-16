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
import java.util.List;

public class DateCommand implements Command {

    private static final Logger log = Logger.getLogger(DateCommand.class);

    RecordService recordService = RecordServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            List<LocalDate> dates = recordService.getAllAvailableDates();
            String json = new ObjectMapper().writeValueAsString(dates);
            response.getWriter().write(json);
            return "response:";
        } catch (IOException e) {
            log.info("Exception when retrieve data");
            e.printStackTrace();
        }

        request.setAttribute("error", "Error in DateCommand");
        return "/WEB-INF/error.jsp";
    }
}
