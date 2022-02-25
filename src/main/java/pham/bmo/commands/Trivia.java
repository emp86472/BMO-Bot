package pham.bmo.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Trivia extends Command {

    String sURL = "https://opentdb.com/api.php?amount=1&type=multiple";
    static LinkedList<TriviaQuestion> tqList = new LinkedList<>();

    public Trivia() {
        super();
        this.setName("Trivia");
        this.setDescription("");
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
            String questionID = this.tqList.getLast().getUserID() + this.tqList.getLast().getQuestionID();
            //System.out.println(questionID);
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Here's a question for you!");
            eb.setDescription(this.tqList.getLast().getQuestion());
            eb.setColor(3974557);
            event.getChannel()
                    .sendMessageEmbeds(eb.build())
                    .setActionRow(Button.primary(questionID + "A","A"),
                            Button.primary(questionID + "B","B"),
                            Button.primary(questionID + "C","C"),
                            Button.primary(questionID + "D","D"))
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

    public LinkedList<TriviaQuestion> getTqList() { return this.tqList; } //getTqList

    public static void sendEmbed(ButtonClickEvent event, EmbedBuilder embed, TriviaQuestion tq, String s) {
        String correctLetter = tq.getCorrectLetter();
        Button A = Button.secondary("w","A").asDisabled();
        Button B = Button.secondary("x","B").asDisabled();
        Button C = Button.secondary("y","C").asDisabled();
        Button D = Button.secondary("z","D").asDisabled();
        HashMap<String, Button> hm = new HashMap<>();
        hm.put("A",A);
        hm.put("B",B);
        hm.put("C",C);
        hm.put("D",D);
        if (correctLetter.equals(s)) {
            hm.replace(s, hm.get(s).withStyle(ButtonStyle.SUCCESS));
        } else {
            hm.replace(s, hm.get(s).withStyle(ButtonStyle.DANGER));
        } //if

        event.getInteraction()
                .getMessage()
                .editMessageEmbeds(embed.build())
                .setActionRow(hm.get("A"),
                        hm.get("B"),
                        hm.get("C"),
                        hm.get("D"))
                .queue();
        event.deferEdit().queue();
    } //sendEmbed
} //Trivia
