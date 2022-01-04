package pham.bmo.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Echo extends Command {

    public Echo() {
        super();
        this.setName("Echo");
        this.setDescription("You get to make BMO say whatever you want! Even if it's weird... we don't judge o.o\n" +
                "Usage: >> echo [message]");
    } //Echo constructor

    @Override
    public void execute(MessageReceivedEvent event) {
        String[] token = Command.getTokens(event);
        if (token[1].equalsIgnoreCase("echo")) {
            String response = "";
            for (int i = 2; i < token.length; i++) {
                response += token[i] + " ";
            } //for
            event.getChannel().sendMessage(response).queue();
        } //if
    } //execute

} //pham.bmo.commands.Echo
