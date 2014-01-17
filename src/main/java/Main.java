import model.Action;
import repository.InMemoryRepository;
import strategy.*;
import tokenizer.SubstringTokenizer;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import static model.Action.*;

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

        Map<Action, Strategy> strategyMap = new HashMap<>();
        strategyMap.put(POST, new PostStrategy());
        strategyMap.put(GET_POSTS, new GetPostsStrategy());
        strategyMap.put(GET_WALL, new GetWallStrategy());
        strategyMap.put(FOLLOW, new FollowStrategy());

        Twitter twitter = new Twitter(
                new SubstringTokenizer(),
                new StrategyExecutor(
                        new InMemoryRepository(),
                        strategyMap),
                new Formatter()
        );

        // User will need to Ctrl-C to quit.
        while (true) {
            String command = c.readLine("> ");
            String out = twitter.something(command);
            c.writer().print(out);
        }
    }
}
