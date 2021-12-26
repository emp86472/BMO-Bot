import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        //conditions that stop the program
        if (event.getMessage().getGuild().getId().equals(BmoBot.testingServerId) != BmoBot.testingMode) return;
        if (event.getAuthor().isBot()) return;
        //if (event.getAuthor().getAsTag().equals("synrg#1396")) return;

        String keyword = event.getMessage().getContentDisplay();
        keyword = keyword.toLowerCase();
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
    } //onMessageReceived
}
