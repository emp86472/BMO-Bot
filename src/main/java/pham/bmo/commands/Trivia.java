package pham.bmo.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Trivia extends Command {

    String sURL = "https://opentdb.com/api.php?amount=1&type=multiple";
    static LinkedList<TriviaQuestion> tqList = new LinkedList<>();
    HashMap<String,String> categoryMap = new HashMap<String,String>();

    public Trivia() {
        super();
        this.setName("Trivia");
        this.categoryMap.put("general", "9");
        this.categoryMap.put("books","10");
        this.categoryMap.put("film","11");
        this.categoryMap.put("music","12");
        this.categoryMap.put("theatre","13");
        this.categoryMap.put("television","14");
        this.categoryMap.put("videogames","15");
        this.categoryMap.put("boardgames","16");
        this.categoryMap.put("nature","17");
        this.categoryMap.put("computers","18");
        this.categoryMap.put("math","19");
        this.categoryMap.put("mythology","20");
        this.categoryMap.put("sports","21");
        this.categoryMap.put("geography","22");
        this.categoryMap.put("history","23");
        this.categoryMap.put("politics","24");
        this.categoryMap.put("art","25");
        this.categoryMap.put("celebrities","26");
        this.categoryMap.put("animals","27");
        this.setDescription("A trivia game from opentdb.com!\n" +
                "Usage: >> trivia [category] [difficulty]\n" +
                "Categories: \n" +
                "- General\n" +
                "- Books\n" +
                "- Film\n" +
                "- Music\n" +
                "- Theatre\n" +
                "- Television\n" +
                "- VideoGames\n" +
                "- BoardGames\n" +
                "- Nature\n" +
                "- Computers\n" +
                "- Math\n" +
                "- Mythology\n" +
                "- Sports\n" +
                "- Geography\n" +
                "- History\n" +
                "- Politics\n" +
                "- Art\n" +
                "- Celebrities\n" +
                "- Animals\n");
    } //trivia

    @Override
    public void execute(MessageReceivedEvent event) {
        String[] token = Command.getTokens(event);
        if (token[1].equalsIgnoreCase("trivia")) {
            //search the list for userIDs that exist, if it does exist replace it
            for (int i = 0; i < this.tqList.size(); i++) {
                if (event.getAuthor().getId().equalsIgnoreCase(this.tqList.get(i).getUserID())) {
                    this.tqList.remove(i);
                    break;
                } //if
            } //for
            //make a hashmap for categories
            if (token.length > 2) {
                if (this.categoryMap.containsKey(token[2].toLowerCase())) {
                    this.sURL += "&category=" + this.categoryMap.get(token[2]);
                } else {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("Sorry that category doesn't exist :/");
                    eb.setDescription("For a list of categories do:\n>> help trivia");
                    eb.setColor(3974557);
                    event.getChannel()
                            .sendMessageEmbeds(eb.build())
                            .queue();
                    return;
                } //if
                if (token.length > 3) {
                    this.sURL += "&difficulty=" + token[3];
                } //if
            }//if
            try {
                this.tqList.add(new TriviaQuestion(this.sURL, event));
            } catch (MalformedURLException exception) {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Sorry that difficulty doesn't exist :/");
                eb.setDescription("Difficulties are easy, medium, and hard");
                eb.setColor(3974557);
                event.getChannel()
                        .sendMessageEmbeds(eb.build())
                        .queue();
                return;
            } //try
            String questionID = this.tqList.getLast().getUserID() + this.tqList.getLast().getPromptID();
            //System.out.println(questionID);
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Here's a question for you!");
            eb.setDescription(this.tqList.getLast().getPrompt());
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
