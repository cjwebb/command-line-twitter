package model;

import java.util.*;

/**
 *
 */
public class Posts implements Iterable<Post> {

    private final List<Post> posts;

    private Posts(List<Post> posts) {
        this.posts = posts;
    }

    public int size() {
        return posts.size();
    }

    @Override
    public Iterator<Post> iterator() {
        return posts.iterator();
    }

    public static final Posts posts(Post post) {
        List<Post> list = new LinkedList<>();
        list.add(post);
        return new Posts(list);
    }

    public static final Posts posts(Set<Post> posts) {
        List<Post> list = new LinkedList<>();
        if (posts != null) {
            for (Post p: posts) {
                list.add(p);
            }
        }
        return new Posts(list);
    }

    public static final Posts empty() {
        return new Posts(new LinkedList<Post>());
    }
}