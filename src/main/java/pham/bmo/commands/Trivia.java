package pham.bmo.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Trivia extends Command {

    String sURL = "https://opentdb.com/api.php?amount=1";

    public Trivia() {
        super();
        this.setName("Trivia");
        this.setDescription("");
    }
    @Override
    public void execute(MessageReceivedEvent event) {
        String[] token = Command.getTokens(event);
        if (token[1].equalsIgnoreCase("trivia")) {

            try {
                URL url = new URL(this.sURL);
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
                String[] answers = new String[jArr.size() + 1];
                answers[0] = elems[4].getAsString(); //the correct answer is always at index 0!!!
                for (int i = 0; i < jArr.size(); i++) {
                    answers[i + 1] = jArr.get(i).getAsString();
                } //for

                //embed stuff
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Here's a question for you!");
                String description = "Category: " + elems[0].getAsString() +
                        "\nType: " + elems[1].getAsString() +
                        "\nDifficulty: " + elems[2].getAsString() +
                        "\nQuestion: " + elems[3].getAsString();
                eb.setDescription(description);
                eb.setColor(3974557);
                event.getChannel().sendMessageEmbeds(eb.build()).queue();

            } catch (MalformedURLException mue) {
                //print out error how to use command
            } catch (IOException ioe) {
                //i think do the same as above
                //this exception shouldn't ever happen O.o
            } //try

        } //if
    } //execute
}
