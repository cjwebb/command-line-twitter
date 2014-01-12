package repository;

import model.Post;
import model.Posts;
import model.User;

/**
 *
 */
public interface Repository {

    public void post(User user, Post post);

    public Posts getUserPosts(User user);

    public Posts getUserWall(User user);

    public void follow(User user, User userToFollow);

}
