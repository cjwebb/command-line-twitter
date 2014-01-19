package formatter;

import clock.FakeSystemClock;
import model.Output;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static model.Post.text;
import static model.Posts.posts;
import static model.User.name;

public class PrettyPrintFormatterTest {

    private FakeSystemClock clock = new FakeSystemClock();
    private PrettyPrintFormatter f = new PrettyPrintFormatter(clock);

    private long ONE_SECOND = 1000;

    @Test
    public void prettyPrints() {
        Output o = new Output(posts(text(name("Alice"), "hello", clock)));

        clock.increment(ONE_SECOND);
        String out = f.format(o);

        assertEquals("Alice - hello (1 second ago)\n", out);
    }
}
