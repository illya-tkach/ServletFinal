package org.itstep.controller.command;

import org.itstep.model.entity.validator.DateAndTimeValidator;
import org.itstep.model.entity.validator.NumberValidator;
import org.itstep.service.RecordService;
import org.itstep.service.impl.RecordServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class BookingCommand implements Command {

    RecordService recordService = RecordServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        NumberValidator numberValidator = new NumberValidator();
        DateAndTimeValidator DTValidator = new DateAndTimeValidator();
        String barberId = request.getParameter("barberId");
        String serviceId = request.getParameter("serviceId");
        String dateAndTime = request.getParameter("dateAndTime");

        if (numberValidator.isValidInteger(barberId) && numberValidator.isValidInteger(serviceId)
                && DTValidator.isValidDateAndTime(dateAndTime)
        ) {
            Optional<Integer> cost = recordService.calculateServiceCost(Integer.parseInt(barberId), Integer.parseInt(serviceId));

            if (cost.isPresent()) {
                request.setAttribute("cost", cost.get());
                return "/WEB-INF/views/paymentView.jsp";
            } else {
                request.setAttribute("error", "Error in Booking Command. Cannot calculate cost");
                return "/WEB-INF/error.jsp";
            }
        } else {
            request.setAttribute("menuError", "Select all parameters for booking");
            return "/WEB-INF/views/employeeTaskView.jsp";
        }
    }
}
