package pham.bmo.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

public class Compliment extends Command {

    private final String[] adverb = {"very", "extremely", "really", "exceptionally", "genuinely"};
    private final String[] adjective = {"pretty", "gorgeous", "beautiful", "amazing", "stunning",
            "adorable", "lovely", "cute"};
    private final String[] emoticon = {":3", ":)", ":D", "!"};
    private final String[] bodypart = {"nails", "eyes", "lips", "eyelashes", "cheeks"};
    private final Random rand = new Random();

    /**
     * Constructor for Compliment
     */
    public Compliment() {
        super();
        this.setName("Compliment");
        this.setDescription("Get a random compliment from BMO!\n" +
                "Usage: >> compliment");
    } //Compliment constructor

    @Override
    public void execute(MessageReceivedEvent event) {
        String[] token = Command.getTokens(event);
        if (token[1].equalsIgnoreCase("compliment")) {
            String response;
            int chance = rand.nextInt(3);
            if (rand.nextInt(50) == 0) {
                event.getChannel().sendMessage("nice ass").queue();
            } else {
                switch (chance) {
                    case 0:
                        response = "You are";
                        response += " " + adverb[rand.nextInt(adverb.length)];
                        response += " " + adjective[rand.nextInt(adjective.length)];
                        response += " " + emoticon[rand.nextInt(emoticon.length)];
                        event.getChannel().sendMessage(response).queue();
                        break;
                    case 1:
                        response = "Your";
                        response += " " + bodypart[rand.nextInt(bodypart.length)] + " are";
                        response += " " + adverb[rand.nextInt(adverb.length)];
                        response += " " + adjective[rand.nextInt(adjective.length)];
                        response += " " + emoticon[rand.nextInt(emoticon.length)];
                        event.getChannel().sendMessage(response).queue();
                        break;
                    case 2:
                        response = "You have";
                        response += " " + adverb[rand.nextInt(adverb.length)];
                        response += " " + adjective[rand.nextInt(adjective.length)];
                        response += " " + bodypart[rand.nextInt(bodypart.length)];
                        response += " " + emoticon[rand.nextInt(emoticon.length)];
                        event.getChannel().sendMessage(response).queue();
                        break;
                } //switch
            } //if
        } //if
    } //execute
} //Command
