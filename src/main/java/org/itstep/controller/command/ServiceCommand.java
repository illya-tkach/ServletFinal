package org.itstep.controller.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.itstep.dto.ServiceDTO;
import org.itstep.service.ServiceService;
import org.itstep.service.impl.ServiceServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServiceCommand implements Command {
    ServiceService service = new ServiceServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            List<ServiceDTO> services = service.findAll();
            String json = new ObjectMapper().writeValueAsString(services);
            response.getWriter().write(json);
            return "response:";
        } catch (IOException e) {
            e.printStackTrace();
        }
        // if something gone wrong
        request.setAttribute("error", "Error in Booking Command");
        return "/WEB-INF/error.jsp";
    }
}
