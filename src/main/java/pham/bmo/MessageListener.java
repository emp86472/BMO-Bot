package pham.bmo;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pham.bmo.commands.Command;
import pham.bmo.commands.Trivia;
import pham.bmo.commands.TriviaQuestion;

import static pham.config.Config.TEST_MODE;
import static pham.config.Config.TEST_SERVER_ID;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class MessageListener extends ListenerAdapter {

    private Random rand = new Random();
    private Trivia t = new Trivia();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //conditions that stop the program
        if (event.getMessage().getGuild().getId().equals(TEST_SERVER_ID) != TEST_MODE) return;
        if (event.getAuthor().isBot()) return;

        String keyword = event.getMessage().getContentRaw();
        keyword = keyword.toLowerCase();
        String[] token = keyword.split("\\s");

        if (token[0].equals(">>")) {
            Command[] command = Command.getCommands();
            for (int i = 0; i < command.length; i++) {
                command[i].execute(event);
            } //for
        } else {
            SimpleResponse.respond(event, keyword);
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
            int qID = tq.getPromptID(); //specific question id
            EmbedBuilder ebcorrect = tq.getCorrectAnswerEB(); //right answer response
            EmbedBuilder ebwrong = tq.getWrongAnswerEB(); //wrong answer response
            HashMap<String,String> hm = tq.getAnswers(); //hashmap of all answers (shuffled)
            String buttonID = event.getComponentId(); //the ID of the button thats been pressed

            if (buttonID.equals(userID + qID + "A")) {
                if (ans.equalsIgnoreCase(hm.get("A"))) {
                    Trivia.sendEmbed(event, ebcorrect, tq, "A");
                } else {
                    Trivia.sendEmbed(event, ebwrong, tq, "A");
                } //if
            } else if (buttonID.equals(userID + qID + "B")) {
                if (ans.equalsIgnoreCase(hm.get("B"))) {
                    Trivia.sendEmbed(event, ebcorrect, tq, "B");
                } else {
                    Trivia.sendEmbed(event, ebwrong, tq, "B");
                } //if
            } else if (buttonID.equals(userID + qID + "C")) {
                if (ans.equalsIgnoreCase(hm.get("C"))) {
                    Trivia.sendEmbed(event, ebcorrect, tq, "C");
                } else {
                    Trivia.sendEmbed(event, ebwrong, tq, "C");
                } //if
            } else if (buttonID.equals(userID + qID + "D")) {
                if (ans.equalsIgnoreCase(hm.get("D"))) {
                    Trivia.sendEmbed(event, ebcorrect, tq, "D");
                } else {
                    Trivia.sendEmbed(event, ebwrong, tq, "D");
                } //if
            } //if
        } //for
    } //onButtonClick


} //MessageListener
