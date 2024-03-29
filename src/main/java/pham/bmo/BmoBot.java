package pham.bmo;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import pham.bmo.commands.Trivia;


import javax.security.auth.login.LoginException;

public class BmoBot {

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createLight(System.getenv().get("BOT_TOKEN"), GatewayIntent.GUILD_MESSAGES).build();
        jda.addEventListener(new MessageListener());
    }
}
