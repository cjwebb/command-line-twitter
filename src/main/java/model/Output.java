package model;

/**
 *
 */
public class Output {

    private Posts posts;

    public Output(Posts posts) {
        this.posts = posts;
    }

    public static Output empty() {
        return new Output(Posts.empty());
    }

    public Posts getPosts() {
        return posts;
    }
}
