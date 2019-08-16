package org.itstep.controller.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.itstep.controller.command.record.DateCommand;
import org.itstep.dto.BarberDTO;
import org.itstep.service.BarberService;
import org.itstep.service.impl.BarberServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BarbersCommand implements Command {

    private static final Logger log = Logger.getLogger(DateCommand.class);

    BarberService barberService = BarberServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.setContentType("application/json");
            List<BarberDTO> barberDTOList = barberService.findAll();
            String json = new ObjectMapper().writeValueAsString(barberDTOList);
            response.getWriter().write(json);
            return "response:";
        } catch (IOException e) {
            log.info("Exception when retrieve data");
            e.printStackTrace();
        }
        //if something gone wrong
        request.setAttribute("error", "Error in BarbersCommand");
        return "/WEB-INF/error.jsp";
    }
}
