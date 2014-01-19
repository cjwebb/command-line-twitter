package config;

import clock.SystemClock;
import formatter.PrettyPrintFormatter;
import model.Action;
import repository.InMemoryRepository;
import strategy.*;
import tokenizer.SubstringTokenizer;
import twitter.Twitter;

import java.util.HashMap;
import java.util.Map;

import static model.Action.*;

public class Config {

    public static Twitter twitter(SystemClock clock){
        return new Twitter(
                new SubstringTokenizer(),
                new StrategyExecutor(
                        new InMemoryRepository(),
                        strategyMap(clock)),
                new PrettyPrintFormatter(clock)
        );
    }

    public static Map<Action, Strategy> strategyMap(SystemClock clock) {
        Map<Action, Strategy> strategyMap = new HashMap<>();
        strategyMap.put(POST, new PostStrategy(clock));
        strategyMap.put(GET_POSTS, new GetPostsStrategy());
        strategyMap.put(GET_WALL, new GetWallStrategy());
        strategyMap.put(FOLLOW, new FollowStrategy());
        return strategyMap;
    }

}
