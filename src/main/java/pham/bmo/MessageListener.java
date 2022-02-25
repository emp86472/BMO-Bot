package pham.bmo;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;
import pham.bmo.commands.Command;
import pham.bmo.commands.Trivia;
import pham.bmo.commands.TriviaQuestion;

import static pham.config.Config.TEST_MODE;
import static pham.config.Config.TEST_SERVER_ID;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MessageListener extends ListenerAdapter {

    private Random rand = new Random();
    private Trivia t = new Trivia();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //conditions that stop the program
        if (event.getMessage().getGuild().getId().equals(TEST_SERVER_ID) != TEST_MODE) return;
        if (event.getAuthor().isBot()) return;

        String[] emoticon = {":3", ":)", ":D", "!"};

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
            } //if
        } //if
    } //onMessageReceived

    //if i want to make other commands that utilize buttons
    // I will have to put trivia buttons in its own class
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        LinkedList<TriviaQuestion> tqList = t.getTqList();
        String userID = event.getUser().getId();

        for (int i = 0; i < tqList.size(); i++) {

            TriviaQuestion tq = tqList.get(i); //trivia question object
            String ans = tq.getCorrectAnswer(); //the correct answer
            int qID = tq.getQuestionID(); //specific question id
            EmbedBuilder ebcorrect = tq.getCorrectAnswerEB(); //right answer response
            EmbedBuilder ebwrong = tq.getWrongAnswerEB(); //wrong answer response
            HashMap<String,String> hm = tq.getAnswers(); //hashmap of all answers (shuffled)
            String buttonID = event.getComponentId(); //the ID of the button thats been pressed

            if (buttonID.equals(userID + qID + "A")) {
                if (ans.equalsIgnoreCase(hm.get("A"))) {
                    sendEmbed(event, ebcorrect, tq, "A");
                } else {
                    sendEmbed(event, ebwrong, tq, "A");
                } //if
            } else if (buttonID.equals(userID + qID + "B")) {
                if (ans.equalsIgnoreCase(hm.get("B"))) {
                    sendEmbed(event, ebcorrect, tq, "B");
                } else {
                    sendEmbed(event, ebwrong, tq, "B");
                } //if
            } else if (buttonID.equals(userID + qID + "C")) {
                if (ans.equalsIgnoreCase(hm.get("C"))) {
                    sendEmbed(event, ebcorrect, tq, "C");
                } else {
                    sendEmbed(event, ebwrong, tq, "C");
                } //if
            } else if (buttonID.equals(userID + qID + "D")) {
                if (ans.equalsIgnoreCase(hm.get("D"))) {
                    sendEmbed(event, ebcorrect, tq, "D");
                } else {
                    sendEmbed(event, ebwrong, tq, "D");
                } //if
            } //if
        } //for
    } //onButtonClick

    private static void sendEmbed(ButtonClickEvent event, EmbedBuilder embed, TriviaQuestion tq, String s) {
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
} //MessageListener
