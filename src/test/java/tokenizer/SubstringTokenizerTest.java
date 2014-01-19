package tokenizer;

import model.Input;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static model.Action.*;
import static org.junit.Assert.assertEquals;

public class SubstringTokenizerTest {

    SubstringTokenizer tokenizer = new SubstringTokenizer();

    @Test
    public void returnsCommandForPosting() {
        Input c = tokenizer.split("Alice -> I love the weather today");

        assertEquals("Alice", c.getUserName());
        assertEquals(POST, c.getAction());
        assertEquals("I love the weather today", c.getActionText());
    }

    @Test
    public void returnsCommandForUserFeed() {
        Input c = tokenizer.split("Alice");

        assertEquals("Alice", c.getUserName());
        assertEquals(GET_POSTS, c.getAction());
        assertEquals("", c.getActionText());
    }

    @Test
    public void returnsCommandForUserWall() {
        Input c = tokenizer.split("Alice wall");

        assertEquals("Alice", c.getUserName());
        assertEquals(GET_WALL, c.getAction());
        assertEquals("", c.getActionText());
    }

    @Test
    public void returnsCommandForUserFollow() {
        Input c = tokenizer.split("Alice follows Bob");

        assertEquals("Alice", c.getUserName());
        assertEquals(FOLLOW, c.getAction());
        assertEquals("Bob", c.getActionText());
    }
}
