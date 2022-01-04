package pham.bmo.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends Command {

    public Help() {
        super();
        this.setName("Help");
        this.setDescription("You just used this command... but anyway, here's how you use it\n" +
                "Usage: >> help [command]");
    } //Echo constructor

    @Override
    public void execute(MessageReceivedEvent event) {
        Command command = null;
        String title = "";
        String description = "";

        String[] token = Command.getTokens(event);
        if (token[1].equalsIgnoreCase("help")) {
            if (token.length > 2) {
                command = this.getCommand(token[2]);
                if (command == null) {
                    title = "Command does not exist";
                    description = "Do '>> help' for more information.";
                } else {
                    title = command.getName() + " Command";
                    description = command.getDescription();
                } //if
            } else {
                title = "Commands";
                description = "Commands follow this format:\n" +
                ">> [command] [argument1] [argument2] ...\n\n" +
                "Available commands:\n";
                Command[] commandList = Command.getCommands();
                for (int i = 0; i < commandList.length; i++) {
                    description += "- " + commandList[i].getName().toLowerCase() + "\n";
                } //for
            } //if
        } //if

        //embed stuff
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setDescription(description);
        eb.setColor(3974557);

        event.getChannel().sendMessageEmbeds(eb.build()).queue();

    } //execute

    /**
     * Returns a command or null if the command does not exist.
     * @param s the token
     * @return the command
     */
    private Command getCommand(String s) {
        Command[] commandList = Command.getCommands();
        Command command = null;
        for (int i = 0; i < commandList.length; i++) {
            if (commandList[i].getName().equalsIgnoreCase(s)) {
                command = commandList[i];
            } //if
        } //for
        return command;
    } //printCommandDescription
}