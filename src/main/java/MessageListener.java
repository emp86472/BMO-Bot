import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class MessageListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        //conditions that stop the program
        if (event.getMessage().getGuild().getId().equals(BmoBot.testingServerId) != BmoBot.testingMode) return;
        if (event.getAuthor().isBot()) return;
        //if (event.getAuthor().getAsTag().equals("synrg#1396")) return;

        //use this for rng
        Random rand = new Random();
        String[] adverb = {"very", "extremely", "really", "exceptionally", "genuinely"};
        String[] adjective = {"pretty", "gorgeous", "beautiful", "amazing", "stunning", "adorable", "lovely", "cute"};
        String[] emoticon = {":3", ":)", ":D", "!"};
        String[] bodypart = {"nails", "eyes", "lips", "eyelashes", "cheeks"};

        String keyword = event.getMessage().getContentDisplay();
        keyword = keyword.toLowerCase();

        String[] result = keyword.split("\\s");
        if (result[0].equals(">>")) {
            try {
                if (result[1].equalsIgnoreCase("compliment")) {
                    String response;
                    int chance = rand.nextInt(3);
                    if (rand.nextInt(100) == 0) {
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
            } catch (IndexOutOfBoundsException ioobe) {
                event.getChannel().sendMessage("invalid command").queue();
            } //try
        } else {
            if (keyword.contains("candies")) {
                event.getChannel().sendMessage("Candies nuts fit in your mouth!? XD").queue();
            } else if (keyword.contains("gargalon")) {
                event.getChannel().sendMessage("Gargalon these balls lmao").queue();
            } else if (keyword.contains("imagine dragons")) {
                event.getChannel().sendMessage("Imagine dragon these balls all over your face! HA").queue();
            } else if (keyword.contains("candice")) {
                event.getChannel().sendMessage("Candice dick fit in your mouth lol").queue();
            } else if (keyword.contains("kansas")) {
                event.getChannel().sendMessage("Kansas dick fit in your mouth? LOL").queue();
            } else if (keyword.contains("joe")) {
                event.getChannel().sendMessage("joe mama xd").queue();
            }else if (keyword.contains("sugondese")) {
                event.getChannel().sendMessage("sugondese NUTS lmaoooo").queue();
            }else if (keyword.contains("ligma")) {
                event.getChannel().sendMessage("ligma balls heh").queue();
            } //if
        } //if


    } //onMessageReceived
}
