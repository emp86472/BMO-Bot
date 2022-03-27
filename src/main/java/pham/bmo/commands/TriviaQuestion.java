package pham.bmo.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class TriviaQuestion {
    private String correctAnswer;
    private String correctLetter;
    private String userID;
    private int promptID;
    private static int count = 0;
    private String prompt;
    private HashMap<String,String> answers = new HashMap();
    private EmbedBuilder correctAnswerEB;
    private EmbedBuilder wrongAnswerEB;

    public TriviaQuestion(String sURL, MessageReceivedEvent event) throws MalformedURLException {
        this.promptID = this.count;
        this.count++;
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
            String[] choices = new String[4];
            choices = new String[jArr.size() + 1];
            choices[0] = elems[4].getAsString(); //the correct answer is always at index 0!!!
            for (int i = 0; i < jArr.size(); i++) {
                choices[i + 1] = jArr.get(i).getAsString();
            } //for
            this.correctAnswer = choices[0];
            choices = Trivia.shuffleArray(choices);
            this.answers.put("A", choices[0]);
            this.answers.put("B", choices[1]);
            this.answers.put("C", choices[2]);
            this.answers.put("D", choices[3]);
            this.correctLetter();

            String multipleChoice = "\nA. " + answers.get("A")
                    + "\nB. " + answers.get("B")
                    + "\nC. " + answers.get("C")
                    + "\nD. " + answers.get("D");
            this.userID = event.getMessage().getAuthor().getId();
            String question = elems[3].getAsString();
            question = StringEscapeUtils.unescapeHtml4(question);
            this.prompt = "<@" + this.userID + ">" +
                    "\nCategory:   " + elems[0].getAsString() +
                    "\nType:       " + elems[1].getAsString() +
                    "\nDifficulty: " + elems[2].getAsString() +
                    "\n\n" + question + multipleChoice;

            setCorrectAnswerEB();
            setWrongAnswerEB();

        }  catch (IOException ioe) {
            //i think do the same as above
            //this exception shouldn't ever happen O.o
        } //try
    } //constructor

    public void setCorrectAnswerEB() {
        this.correctAnswerEB = new EmbedBuilder();
        this.correctAnswerEB.setTitle(":white_check_mark: You are correct! Nice");
        this.correctAnswerEB.setDescription(this.prompt +
                "\nThe correct answer is: " +
                this.correctAnswer);
        this.correctAnswerEB.setColor(3974557);
    } //setCorrectAnswerEB

    public void setWrongAnswerEB() {
        this.wrongAnswerEB = new EmbedBuilder();
        this.wrongAnswerEB.setTitle(":x: You fucking dumbass LMAO");
        this.wrongAnswerEB.setDescription(this.prompt +
                "\nThe correct answer is: " +
                "**" + this.correctAnswer + "**");
        this.wrongAnswerEB.setColor(3974557);
    } //setWrongAnswerEB

    public String getPrompt() {
        return this.prompt;
    } //getPrompt

    public String getUserID() {
        return this.userID;
    } //getUserId

    public HashMap<String, String> getAnswers() {
        return this.answers;
    } //getAnswers

    public String getCorrectAnswer() {
        return this.correctAnswer;
    } //getCorrectAnswer

    public int getPromptID() { return this.promptID; } //getPromptID

    public EmbedBuilder getCorrectAnswerEB() { return this.correctAnswerEB; }

    public EmbedBuilder getWrongAnswerEB() { return this.wrongAnswerEB; }

    public String getCorrectLetter() {
        return this.correctLetter;
    } //getCorrectLetter

    /**
     * Finding the letter corresponding to the correct answer after the shuffle
     */
    private void correctLetter() {
        if (this.correctAnswer.equalsIgnoreCase(this.answers.get("A"))) {
            this.correctLetter = "A";
        } else if (correctAnswer.equalsIgnoreCase(this.answers.get("B"))) {
            this.correctLetter = "B";
        } else if (correctAnswer.equalsIgnoreCase(this.answers.get("C"))) {
            this.correctLetter = "C";
        } else if (correctAnswer.equalsIgnoreCase(this.answers.get("D"))) {
            this.correctLetter = "D";
        } //if
    } //correctLetter
} //Trivia Question
