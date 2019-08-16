package org.itstep.controller.command;

import org.itstep.controller.utils.AppUtils;
import org.itstep.model.entity.Client;
import org.itstep.model.entity.UserAccount;
import org.itstep.model.entity.validator.ClientValidator;
import org.itstep.model.entity.validator.UserValidator;
import org.itstep.service.UserService;
import org.itstep.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegistrationCommand implements Command {

    UserService userService = new UserServiceImpl();

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

    private String doGet(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/views/registration.jsp";
    }

    private String doPost(HttpServletRequest request, HttpServletResponse response) {
        ClientValidator clientValidator = new ClientValidator();
        UserValidator userValidator = new UserValidator();

        Client client = Client.newBuilder()
                .setFirstName(request.getParameter("firstName"))
                .setLastName(request.getParameter("lastName"))
                .setEmail(request.getParameter("email"))
                .build();
        UserAccount userAccount = UserAccount.newBuilder()
                .setUserName(request.getParameter("userName"))
                .setPassword(request.getParameter("password"))
                .build();

        clientValidator.validate(client);
        userValidator.validate(userAccount);

        if (clientValidator.hasErrors() || userValidator.hasErrors()) {
            if (clientValidator.hasErrors())
                clientValidator.setErrorAttributes(request);
            if (userValidator.hasErrors())
                userValidator.setErrorAttributes(request);
            return "/WEB-INF/views/registration.jsp";

        } else {
            try {
                client.setUserAccount(userAccount);

                UserAccount user = userService.save(client);

                AppUtils.storeLoginedUser(request.getSession(), user);

                return "/WEB-INF/views/homeView.jsp";
            } catch (SQLException e) {
                String errorMessage = e.getMessage();

                request.setAttribute("errorMessage", errorMessage);

                return "/WEB-INF/views/registration.jsp";
            }
        }
    }
}
