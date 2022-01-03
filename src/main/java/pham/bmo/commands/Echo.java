package pham.bmo.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Echo implements Command {
    public void execute(String s, MessageReceivedEvent event) {
        String[] result = s.split("\\s");
        if (result[1].equalsIgnoreCase("echo")) {
            String response = "";
            for (int i = 2; i < result.length; i++) {
                response += result[i] + " ";
            } //for
            event.getChannel().sendMessage(response).queue();

        } else return;
    } //execute
} //pham.bmo.commands.Echo
