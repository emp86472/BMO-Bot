import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class BmoBot {

    //default modifier package private
    final static boolean testingMode = false;
    final static String testingServerId = "197431852225527809";
    private final static String token = "OTE0Mzk1NTQzMTQzMzMzODg4.YaMbNg.GY2ICsiM0ZXl_cVSjfjBaY5zSCY";

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES).build();
        jda.addEventListener(new MessageListener());
    }
}
