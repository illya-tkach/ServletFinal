package org.itstep.controller.command;

import org.itstep.model.entity.Client;
import org.itstep.model.entity.UserAccount;
import org.itstep.service.ClientService;
import org.itstep.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class PersonalCommand implements Command {
    ClientService clientService = new ClientServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("loginedUser");
        Optional<Client> client = clientService.findClient(userAccount.getId());
        if (client.isPresent()) {
            request.setAttribute("client", client.get());
            return "/WEB-INF/views/personal.jsp";
        } else {
            request.setAttribute("error", "Error in PersonalCommand");
            return "/WEB-INF/error.jsp";
        }
    }
}
