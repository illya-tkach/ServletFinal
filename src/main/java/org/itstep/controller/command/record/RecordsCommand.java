package org.itstep.controller.command.record;

import org.itstep.controller.command.Command;
import org.itstep.model.entity.Record;
import org.itstep.service.RecordService;
import org.itstep.service.impl.RecordServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RecordsCommand implements Command {

    RecordService recordService = RecordServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Record> recordList = recordService.findAllReservedRecords();

        request.setAttribute("recordList", recordList);

        return "/WEB-INF/views/listRecords.jsp";
    }
}
