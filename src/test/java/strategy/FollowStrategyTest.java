package strategy;

import model.Action;
import model.Input;
import model.User;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import repository.Repository;

public class FollowStrategyTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private FollowStrategy s = new FollowStrategy();

    private final Repository repo = context.mock(Repository.class);
    private Input input = new Input("userName", Action.FOLLOW, "actionText");

    @Test
    public void callsRepoToFollow() {
        context.checking(new Expectations() {{
            oneOf(repo).follow(User.name("userName"), User.name("actionText"));
        }});
        s.execute(repo, input);
    }
}
