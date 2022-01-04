package pham.bmo.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 *  Abstract class for commands
 */
public abstract class Command {

    private String name;
    private String description;

    /**
     * Determines the BMO's next action after a command is issued.
     * @param event the event that occurs when a message is received
     */
    public abstract void execute(MessageReceivedEvent event);

    /**
     * Returns the name of the command.
     * @return the name
     */
    public String getName() {
        return this.name;
    } //getName;

    /**
     * Returns the description of the command.
     * @return the description
     */
    public String getDescription() {
        return this.description;
    } //getDescription

    /**
     * Sets the name of the command.
     * @param s the name to set
     */
    public void setName(String s) {
        this.name = s;
    } //setName

    /**
     * Sets the description of the command.
     * @param s the description to set
     */
    public void setDescription(String s) {
        this.description = s;
    } //setDescription

    /**
     *
     * @param event the event that occurs when a message is received
     * @return the tokens of the command issued by the user.
     */
    public static String[] getTokens(MessageReceivedEvent event) {
        String keyword = event.getMessage().getContentRaw();
        keyword = keyword.toLowerCase();
        return keyword.split("\\s");
    } //getTokens

    /**
     * Returns an array of all the commands that extend the {@code Command} class.
     * Should be updated each time a new command is added.
     * @return an array of all the commands
     */
    public static Command[] getCommands() {
        return new Command[]{new Compliment(), new Echo(), new Help()};
    } //getCommands
} //Command
