import clock.CurrentDateSystemClock;
import clock.SystemClock;
import twitter.Twitter;

import java.io.Console;

import static config.Config.twitter;

public class Main {

    public static void main(String[] args) {
        Console c = System.console();
        if (c == null) {
            System.err.println("No console found.");
            System.exit(1);
        }

        SystemClock clock = new CurrentDateSystemClock();
        Twitter twitter = twitter(clock);

        // User will need to Ctrl-C to quit.
        while (true) {
            String command = c.readLine("> ");
            String out = twitter.run(command);
            c.writer().print(out);
        }
    }
}
