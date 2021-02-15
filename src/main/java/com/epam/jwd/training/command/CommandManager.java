package com.epam.jwd.training.command;

public enum CommandManager {
//    LOGIN(LoginCommand.INSTANCE),
//    LOGOUT(LogoutCommand.INSTANCE),
    DEFAULT(ShowMainPageCommand.INSTANCE),
    SHOW_COURSES(ShowAllCoursesCommand.INSTANCE);

    private final Command command;

    CommandManager(Command command) {
        this.command = command;
    }

    static Command resolveCommandByName(String name) {
        for (CommandManager action : values()) {
            if (action.name().equalsIgnoreCase(name)) {
                return action.command;
            }
        }
        return DEFAULT.command;
    }

}
