package repository;

import clock.FakeSystemClock;
import model.Post;
import model.Posts;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;

import static model.Post.text;
import static model.User.name;
import static org.junit.Assert.assertEquals;

public class InMemoryRepositoryTest {

    private FakeSystemClock clock = new FakeSystemClock(); // if only there were implicits
    private InMemoryRepository repo = new InMemoryRepository();

    private User alice = name("Alice");
    private User bob = name("Bob");
    private User charlie = name("Charlie");

    @Test
    public void getUserPostsReturnsPostsInReverseOrder() {
        repo.post(alice, text(alice, "hello", clock));
        incrementClock();
        repo.post(alice, text(alice, "hello again", clock));

        Posts alicePosts = repo.getUserPosts(alice);
        Iterator<Post> it = alicePosts.iterator();

        assertEquals(2, alicePosts.size());
        assertEquals("hello again", it.next().getText());
        assertEquals("hello", it.next().getText());
    }

    @Test
    public void getUserPostsAreSeperatedByUsername() {
        Post alicePost = text(alice, "I love the weather today", clock);
        incrementClock();
        Post bobPost = text(bob, "Damn! We lost!", clock);

        repo.post(alice, alicePost);
        repo.post(bob, bobPost);

        Posts alicePosts = repo.getUserPosts(alice);
        Posts bobPosts = repo.getUserPosts(bob);

        assertEquals(1, alicePosts.size());
        assertEquals(alicePost, alicePosts.iterator().next());

        assertEquals(1, bobPosts.size());
        assertEquals(bobPost, bobPosts.iterator().next());
    }

    @Test
    public void getUserWallReturnsOwnPosts() {
        Post charliePost = text(charlie, "I'm in New York today! Anyone wants to have a coffee?", clock);
        incrementClock();
        Post charliePost2 = text(charlie, "Anyone?", clock);
        repo.post(charlie, charliePost);
        repo.post(charlie, charliePost2);

        Posts charliePosts = repo.getUserWall(charlie);
        Iterator<Post> it = charliePosts.iterator();

        assertEquals(2, charliePosts.size());
        assertEquals(charliePost2, it.next());
        assertEquals(charliePost, it.next());
    }

    @Test
    public void getUserWallReturnsOldPostsFromUsersBeingFollowed() {
        Post alicePost = text(alice, "I love the weather today", clock);
        incrementClock();
        Post charliePost = text(charlie, "I'm in New York today! Anyone wants to have a coffee?", clock);

        repo.post(alice, alicePost);
        repo.post(charlie, charliePost);

        repo.follow(charlie, alice);

        Posts charliePosts = repo.getUserWall(charlie);
        Iterator<Post> it = charliePosts.iterator();

        assertEquals(2, charliePosts.size());
        assertEquals(charliePost, it.next());
        assertEquals(alicePost, it.next());
    }

    @Test
    public void getUserWallReturnsNewPostsForOtherUsers() {
        Post alicePost = text(alice, "I love the weather today", clock);

        repo.follow(charlie, alice);
        repo.post(alice, alicePost);

        Posts charliePosts = repo.getUserWall(charlie);

        assertEquals(1, charliePosts.size());
        assertEquals(alicePost, charliePosts.iterator().next());
    }

    private void incrementClock() {
        clock.increment(1000);
    }
}
