import clock.FakeSystemClock;
import org.junit.Test;
import twitter.Twitter;

import static config.Config.twitter;
import static junit.framework.Assert.assertEquals;

public class TwitterTest {

    private FakeSystemClock clock = new FakeSystemClock();

    private Twitter twitter = twitter(clock);

    @Test
    public void postAndRetrieve() {
        clock.setTime(0);
        twitter.run("Alice -> Hello");

        clock.setTime(1000);
        String out = twitter.run("Alice");

        assertEquals("Alice - Hello (1 second ago)\n", out);
    }

    @Test
    public void wallContainsFollows() {
        clock.setTime(0);
        twitter.run("Alice -> Hi");

        clock.setTime(1000);
        twitter.run("Bob -> Hi");

        clock.setTime(2000);
        twitter.run("Alice follows Bob");
        String out = twitter.run("Alice wall");

        assertEquals("Bob - Hi (1 second ago)\nAlice - Hi (2 seconds ago)\n", out);
    }
}
