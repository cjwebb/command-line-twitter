package strategy;

import clock.CurrentDateSystemClock;
import clock.SystemClock;
import model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import repository.Repository;

import java.util.HashMap;
import java.util.Map;

import static model.Action.FOLLOW;
import static model.Post.text;
import static model.Posts.posts;
import static model.User.name;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class StrategyExecutorTest {
    SystemClock clock = new CurrentDateSystemClock();

    @Test
    public void executesStrategy() {
        Posts posts = posts(text(name("Colin"), "the test works!", clock));

        Repository r = new NoOpRepository();
        Map<Action, Strategy> map = new HashMap<>();
        map.put(FOLLOW, new FakeStrategy(posts));

        StrategyExecutor executor = new StrategyExecutor(r, map);
        Output output = executor.execute(new Input("anyUserName", FOLLOW, "anything"));

        assertEquals(posts, output.getPosts());

    }

    public class FakeStrategy implements Strategy {

        private Posts postsToReturn;

        public FakeStrategy(Posts postsToReturn) {
            this.postsToReturn = postsToReturn;
        }

        @Override
        public Output execute(Repository repository, Input input) {
            return new Output(postsToReturn);
        }
    }

    public class NoOpRepository implements Repository {
        @Override
        public void post(User user, Post post) {}

        @Override
        public Posts getUserPosts(User user) { return Posts.empty(); }

        @Override
        public Posts getUserWall(User user) { return Posts.empty(); }

        @Override
        public void follow(User user, User userToFollow) { }
    }
}
