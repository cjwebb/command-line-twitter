package model.comparator;

import clock.FakeSystemClock;
import model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static model.User.name;
import static org.junit.Assert.assertEquals;

public class PostDateComparatorTest {

    FakeSystemClock clock = new FakeSystemClock();
    PostDateComparator c = new PostDateComparator();

    @Test
    public void postIsAfterIfDateIsOlder() throws InterruptedException {
        Post p1 = Post.text(name("alice"), "text", clock);
        clock.increment(1000);
        Post p2 = Post.text(name("bob"), "text", clock);

        assertEquals(1, c.compare(p1, p2));
    }
}
