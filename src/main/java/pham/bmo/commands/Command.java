package pham.bmo.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 *  Functional interface for adding commands
 */
public interface Command {
    public void execute(String s, MessageReceivedEvent event);
} //pham.bmo.commands.Command
