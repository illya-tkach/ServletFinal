package org.itstep.controller.command;

import org.itstep.dto.BarberDTO;
import org.itstep.model.entity.validator.NumberValidator;
import org.itstep.service.BarberService;
import org.itstep.service.RecordService;
import org.itstep.service.impl.BarberServiceImpl;
import org.itstep.service.impl.RecordServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeParseException;
import java.util.List;


public class AddNewRecord implements Command {

    BarberService barberService = BarberServiceImpl.getInstance();
    RecordService recordService = RecordServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String requestMethod = request.getMethod();

        if (requestMethod.equals("GET"))
            return doGet(request, response);
        else if (requestMethod.equals("POST"))
            return doPost(request, response);
        else
            return "/WEB-INF/error/405.jsp";
    }

    private String doGet(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        if (id != null) {
            recordService.remove(Integer.parseInt(id));
            return "redirect:recordList";
        } else {
            List<BarberDTO> barbers = barberService.findAll();
            req.setAttribute("barbers", barbers);
            return "/WEB-INF/views/newRecordPage.jsp";
        }
    }

    private String doPost(HttpServletRequest req, HttpServletResponse resp) {
        NumberValidator numberValidator = new NumberValidator();

        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String barberId = req.getParameter("barberId");

        if (numberValidator.isValidInteger(barberId)) {
            try {
                recordService.save(Integer.parseInt(barberId), date, time);
                return "redirect:recordList";
            } catch (DateTimeParseException e) {
                req.setAttribute("dateAndTimeError", "Wrong time or date");
                return "/WEB-INF/views/newRecordPage.jsp";
            }
        } else {
            req.setAttribute("barberError", "Select barber please");
            return "/WEB-INF/views/newRecordPage.jsp";
        }
    }
}
