package strategy;

import clock.FakeSystemClock;
import clock.SystemClock;
import model.Action;
import model.Input;
import model.Post;
import model.User;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import repository.Repository;

import static model.User.name;

/**
 *
 */
public class PostStrategyTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private final SystemClock clock = new FakeSystemClock();

    private PostStrategy s = new PostStrategy(clock);

    private final Repository repo = context.mock(Repository.class);
    private Input input = new Input("userName", Action.POST, "actionText");

    @Test
    public void callsRepoToFollow() {
        final User user = name("userName");
        context.checking(new Expectations() {{
            oneOf(repo).post(user, Post.text(user, "actionText", clock));
        }});
        s.execute(repo, input);
    }

}
