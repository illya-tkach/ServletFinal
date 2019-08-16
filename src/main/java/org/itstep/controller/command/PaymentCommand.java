package org.itstep.controller.command;

import org.itstep.model.entity.UserAccount;
import org.itstep.service.BarberService;
import org.itstep.service.RecordService;
import org.itstep.service.impl.BarberServiceImpl;
import org.itstep.service.impl.RecordServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PaymentCommand implements Command {
    RecordService recordService = RecordServiceImpl.getInstance();
    BarberService barberService = BarberServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int barberId = Integer.valueOf(request.getParameter("barberId"));
        int serviceId = Integer.valueOf(request.getParameter("serviceId"));

        String dateAndTime = request.getParameter("dateAndTime");
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("loginedUser");
        try {
            barberService.findByServiceAndDate(barberId, serviceId, dateAndTime);
        } catch (SQLException e){
            request.setAttribute("error", e.getMessage());
            return errorReturn(request);
        }
        try {
            recordService.payAndSaveRecord(barberId, serviceId, dateAndTime, userAccount.getId());
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
            return errorReturn(request);
        }

        request.setAttribute("bookingSuccess", "You are successfully added your record to the system.");
        return "/WEB-INF/views/homeView.jsp";
    }
    private String errorReturn(HttpServletRequest request){
        String cost = request.getParameter("cost");
        request.setAttribute("cost", cost);
        return "/WEB-INF/views/paymentView.jsp";
    };
}
