package org.itstep.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientMenuCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/views/employeeTaskView.jsp";
    }
}
