package pham.bmo.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.Button;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Trivia extends Command {

    String sURL = "https://opentdb.com/api.php?amount=1&type=multiple";
    static LinkedList<TriviaQuestion> tqList;

    public Trivia() {
        super();
        this.setName("Trivia");
        this.setDescription("");
        this.tqList = new LinkedList<>();
    }
    @Override
    public void execute(MessageReceivedEvent event) {
        String[] token = Command.getTokens(event);
        if (token[1].equalsIgnoreCase("trivia")) {
            //search the list for userIDs that exist, if it does exist replace it
            for (int i = 0; i < tqList.size(); i++) {
                if (event.getAuthor().getId().equalsIgnoreCase(tqList.get(i).getUserID())) {
                    tqList.remove(i);
                    break;
                } //if
            } //for
            this.tqList.add(new TriviaQuestion(this.sURL, event));
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Here's a question for you!");
            eb.setDescription(tqList.getLast().getQuestion());
            eb.setColor(3974557);
            event.getChannel()
                    .sendMessageEmbeds(eb.build())
                    .setActionRow(Button.primary("1","A"),
                            Button.primary("2","B"),
                            Button.primary("3","C"),
                            Button.primary("4","D"))
                    .queue();

        } //if
    } //execute

    //I have to return a new array because I can't pass by reference in java >.<
    //This could go in a util class later C:
    public static <T> T[] shuffleArray(T[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            T temp = array[i];
            int x = rand.nextInt(array.length);
            array[i] = array[x];
            array[x] = temp;
        } //for
        return array;
    } // shuffleArray
} //Trivia