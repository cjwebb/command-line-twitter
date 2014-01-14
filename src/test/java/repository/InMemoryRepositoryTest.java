package repository;

import model.Post;
import model.Posts;
import model.User;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;

import static model.User.name;
import static model.Post.text;

/**
 * todo:
 *      1. use a system clock, and increment that, instead of sleeping
 *      2. tidy up
 *
 */
@RunWith(JUnit4.class)
public class InMemoryRepositoryTest {

    private InMemoryRepository repo = new InMemoryRepository();

    private User alice = name("Alice");
    private User bob = name("Bob");
    private User charlie = name("Charlie");

    @Test
    public void getUserPostsReturnsPostsInReverseOrder() {
        repo.post(alice, text(alice, "hello"));
        sleep(100);
        repo.post(alice, text(alice, "hello again"));

        Posts alicePosts = repo.getUserPosts(alice);
        Iterator<Post> it = alicePosts.iterator();

        assertEquals(2, alicePosts.size());
        assertEquals("hello again", it.next().getText());
        assertEquals("hello", it.next().getText());
    }

    @Test
    public void getUserPostsAreSeperatedByUsername() {
        Post alicePost = text(alice, "I love the weather today");
        sleep(100);
        Post bobPost = text(bob, "Damn! We lost!");

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
        Post charliePost = text(charlie, "I'm in New York today! Anyone wants to have a coffee?");
        sleep(100);
        Post charliePost2 = text(charlie, "Anyone?");
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
        Post alicePost = text(alice, "I love the weather today");
        sleep(100);
        Post charliePost = text(charlie, "I'm in New York today! Anyone wants to have a coffee?");

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
        Post alicePost = text(alice, "I love the weather today");

        repo.follow(charlie, alice);
        repo.post(alice, alicePost);

        Posts charliePosts = repo.getUserWall(charlie);

        assertEquals(1, charliePosts.size());
        assertEquals(alicePost, charliePosts.iterator().next());
    }

    // todo, remove by making posts not ordered by java.util.Date (inject system clock interface)
    private void sleep(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
