package strategy;

import clock.CurrentDateSystemClock;
import clock.SystemClock;
import model.Action;
import model.Input;
import model.Output;
import model.Posts;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import repository.Repository;

import static model.Post.text;
import static model.Posts.posts;
import static model.User.name;
import static org.junit.Assert.assertEquals;

public class GetPostsStrategyTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private GetPostsStrategy s = new GetPostsStrategy();

    private final SystemClock clock = new CurrentDateSystemClock();
    private final Repository repo = context.mock(Repository.class);
    private Input input = new Input("userName", Action.GET_POSTS, "actionText");

    private Posts posts = posts(text(name("userName"), "actionText", clock));

    @Test
    public void callsRepoToGetPosts() {
        context.checking(new Expectations() {{
            oneOf(repo).getUserPosts(name("userName"));
                will(returnValue(posts));
        }});

        Output o = s.execute(repo, input);

        assertEquals(posts, o.getPosts());
    }

}
