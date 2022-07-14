package pham.bmo;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

public class SimpleResponse {
    public static void respond(MessageReceivedEvent event, String keyword) {
        String[] emoticon = {":3", ":)", ":D", "!"};
        Random rand = new Random();
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
        } else if (keyword.contains("boba") && odds(10)) {
            event.getMessage().reply("stop buying boba kaitlyn you broke bitch").queue();
        } else if (keyword.contains("kaitlyn") && odds(10)) {
            event.getMessage().reply("she is my maid wassup").queue();
        } else if (keyword.contains("aadarsh") && odds(10)) {
            event.getMessage().reply("why yall mention my wife for").queue();
        } //ifw
    } //response

    /**
     * Useful library class later
     *
     * @param percentage
     * @return boolean
     */
    private static boolean odds(int percentage) {
        Random rand = new Random();
        return rand.nextInt(1000) < (percentage * 10);
    } //odds
}
