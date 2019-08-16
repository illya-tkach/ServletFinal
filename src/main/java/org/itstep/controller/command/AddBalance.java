package org.itstep.controller.command;

import org.apache.log4j.Logger;
import org.itstep.model.entity.UserAccount;
import org.itstep.model.entity.validator.NumberValidator;
import org.itstep.service.ClientService;
import org.itstep.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBalance implements Command {

    private static final Logger log = Logger.getLogger(AddBalance.class);

    ClientService clientService = new ClientServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        NumberValidator numberValidator = new NumberValidator();

        String money = request.getParameter("money");

        if (numberValidator.isValidInteger(money)) {
            UserAccount userAccount = (UserAccount) request.getSession().getAttribute("loginedUser");
            clientService.addMoneyToBalance(Integer.parseInt(money), userAccount.getId());
            log.info("Add " + money + " to " + userAccount.getId() + " user ID");
            response.setStatus(201);
            return "response:";
        } else {
            response.setStatus(400);
            return "response:";
        }
    }
}
