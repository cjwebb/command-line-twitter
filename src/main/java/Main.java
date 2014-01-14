import repository.InMemoryRepository;
import tokenizer.TwitterTokenizer;

import java.io.Console;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Console c = System.console();
        if (c == null) {
            System.err.println("No console found.");
            System.exit(1);
        }

        Twitter twitter = new Twitter(
                new TwitterTokenizer(),
                new Logic(new InMemoryRepository()),
                new Formatter()
        );

        // User will need to Ctrl-C to quit.
        while (true) {
            String command = c.readLine("> ");
            String out = twitter.something(command);
            c.writer().println(out);
        }
    }
}
