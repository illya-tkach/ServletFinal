package org.itstep.controller.command;

import org.itstep.controller.command.record.DateCommand;
import org.itstep.controller.command.record.RecordsCommand;
import org.itstep.controller.command.record.TimeCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {

    /* Command holder map */
    private Map<String, Command> commands;

    /**
     * Constructs Command Holder
     */
    public CommandHolder() {
        initCommands();
    }

    /**
     * Initialize commands
     */
    private void initCommands() {
        commands = new HashMap<String, Command>() {
            {
                put("employeeTask", new ClientMenuCommand());
                put("", new HomeCommand());
                put("logout", new LogoutCommand());
                put("login", new LoginCommand());
                put("registration", new RegistrationCommand());
                put("barbersAll", new BarbersCommand());
                put("servicesAll", new ServiceCommand());
                put("dateAll", new DateCommand());
                put("timeAll", new TimeCommand());
                put("booking", new BookingCommand());
                put("payment", new PaymentCommand());
                put("recordList", new RecordsCommand());
                put("personal", new PersonalCommand());
                put("addMoney", new AddBalance());
                put("newRecord", new AddNewRecord());
            }
        };
    }

    /**
     * Get command from commands holder
     *
     * @param key command key
     * @return command object or default generated command
     */
    public Command getCommand(String key) {
        return commands.getOrDefault(key, (request, response) -> "/homeView.jsp");
    }
}
