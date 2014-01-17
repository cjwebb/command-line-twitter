package repository;

import model.Post;
import model.Posts;
import model.User;

import java.util.*;

/**
 *
 */
public class InMemoryRepository implements Repository {

    private Map<User, Set<Post>> userPosts = new HashMap<>();
    private Map<User, Set<Post>> userWall = new HashMap<>();
    private Map<User, Set<User>> userFollowers = new HashMap<>();

    @Override
    public void post(User user, Post post) {
        add(user, post, userPosts);
        add(user, post, userWall);

        addToFollowersWalls(user, post);
    }

    private void add(User user, Post post, Map<User, Set<Post>> map) {
        Set<Post> posts = emptyPostSetIfNull(map.get(user));
        posts.add(post);
        map.put(user, posts);
    }

    private void addToFollowersWalls(User user, Post post) {
        Set<User> followers = emptyUserSetIfNull(userFollowers.get(user));
        for (User u: followers) {
            add(u, post, userWall);
        }
    }

    private Set<User> emptyUserSetIfNull(Set<User> s) {
        return s == null ? new HashSet<User>() : s;
    }

    private Set<Post> emptyPostSetIfNull(Set<Post> s) {
        // todo post comparator
        return s == null ? new TreeSet<Post>() : s;
    }

    @Override
    public Posts getUserPosts(User user) {
        Set<Post> posts = emptyPostSetIfNull(userPosts.get(user));
        return Posts.posts(posts);
    }

    @Override
    public Posts getUserWall(User user) {
        Set<Post> posts = userWall.get(user);
        return Posts.posts(posts);
    }

    @Override
    public void follow(User user, User userToFollow) {
        // add to set
        Set<User> followers = emptyUserSetIfNull(userFollowers.get(userToFollow));
        followers.add(user);
        userFollowers.put(userToFollow, followers);

        // update wall
        Posts posts = getUserPosts(userToFollow);
        for (Post p: posts) {
            add(user, p, userWall);
        }
    }
}
