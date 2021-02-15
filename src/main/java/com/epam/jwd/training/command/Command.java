package com.epam.jwd.training.command;

public interface Command {

    ResponseContext execute(RequestContext request);

    //todo: name convention
    static Command resolveCommandByName(String name) {
        return CommandManager.resolveCommandByName(name);
    }

}
