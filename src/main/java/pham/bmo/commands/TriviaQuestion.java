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

public class TriviaQuestion {
    private String correctAnswer;
    private String userID;
    private String[] answers;
    private String question;

    public TriviaQuestion(String sURL, MessageReceivedEvent event) {
        try {
            URL url = new URL(sURL);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JsonElement jsonElem = JsonParser.parseReader(reader);
            JsonObject jObj = jsonElem.getAsJsonObject();
            JsonArray jArr = jObj.getAsJsonArray("results");
            jObj = jArr.get(0).getAsJsonObject();
            JsonElement[] elems = {jObj.get("category"),
                    jObj.get("type"),
                    jObj.get("difficulty"),
                    jObj.get("question"),
                    jObj.get("correct_answer"),
                    jObj.get("incorrect_answers")};
            jArr = elems[5].getAsJsonArray();
            this.answers = new String[jArr.size() + 1];
            answers[0] = elems[4].getAsString(); //the correct answer is always at index 0!!!
            for (int i = 0; i < jArr.size(); i++) {
                answers[i + 1] = jArr.get(i).getAsString();
            } //for
            this.correctAnswer = answers[0];
            HashMap<String,String> hm = new HashMap();
            answers = Trivia.shuffleArray(answers);
            hm.put("A", answers[0]);
            hm.put("B", answers[1]);
            hm.put("C", answers[2]);
            hm.put("D", answers[3]);
            String multipleChoice = "\nA. " + hm.get("A")
                    + "\nB. " + hm.get("B")
                    + "\nC. " + hm.get("C")
                    + "\nD. " + hm.get("D");
            this.userID = event.getMessage().getAuthor().getId();
            this.question = "<@" + this.userID + ">" +
                    "\nCategory:     " + elems[0].getAsString() +
                    "\nType:       " + elems[1].getAsString() +
                    "\nDifficulty: " + elems[2].getAsString() +
                    "\n\n" + elems[3].getAsString() + multipleChoice;

            //try making these button static instance variables (make an array of buttons)
            //then in messageListener disable the buttons
            //since they are static disabling them should disable all buttons across all trivia objects
            //you might have to re-enable the buttons on another call to trivia, not sure tho :/

        } catch (MalformedURLException mue) {
            //print out error how to use command
        } catch (IOException ioe) {
            //i think do the same as above
            //this exception shouldn't ever happen O.o
        } //try
    } //constructor

    public String getQuestion() {
        return this.question;
    } //getQuestion

    public String getUserID() {
        return this.userID;
    } //getUserId

    public String[] getAnswers() {
        return this.answers;
    } //getAnswers

    public String getCorrectAnswer() {
        return this.correctAnswer;
    } //getCorrectAnswer
} //Trivia Question
