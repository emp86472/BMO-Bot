package pham.bmo;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pham.bmo.commands.Command;

import java.util.Random;

public class MessageListener extends ListenerAdapter {

    private Random rand = new Random();

    public void onMessageReceived(MessageReceivedEvent event) {
        //conditions that stop the program
        if (event.getMessage().getGuild().getId().equals(BmoBot.testingServerId) != BmoBot.testingMode) return;
        if (event.getAuthor().isBot()) return;


        //use this for rng
        //Random rand = new Random();
        String[] adverb = {"very", "extremely", "really", "exceptionally", "genuinely"};
        String[] adjective = {"pretty", "gorgeous", "beautiful", "amazing", "stunning", "adorable", "lovely", "cute"};
        String[] emoticon = {":3", ":)", ":D", "!"};
        String[] bodypart = {"nails", "eyes", "lips", "eyelashes", "cheeks"};

        String keyword = event.getMessage().getContentRaw();
        keyword = keyword.toLowerCase();
        String[] token = keyword.split("\\s");

        if (token[0].equals(">>")) {
            Command[] command = Command.getCommands();
            for (int i = 0; i < command.length; i++) {
                command[i].execute(event);
            } //for
        } else {
            if (event.getAuthor().getAsTag().equals("synrg#1396")) return;
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
            } else if (keyword.contains("sugondese")) {
                event.getChannel().sendMessage("sugondese NUTS lmaoooo").queue();
            } else if (keyword.contains("ligma")) {
                event.getChannel().sendMessage("ligma balls heh").queue();
            } else if (keyword.contains("liek")) {
                event.getChannel().sendMessage("You misspelled 'like' " + emoticon[rand.nextInt(emoticon.length)]).queue();
            } else if (keyword.contains("i love you")) {
                event.getMessage().reply("Geez, get a room .-.").queue();
            } else if (keyword.contains(" mad ")) {
                event.getChannel().sendMessage("madge? lmao").queue();
            } //if
        } //if
    } //onMessageReceived
} //MessageListener