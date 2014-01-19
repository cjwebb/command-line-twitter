package model;

public final class Output {

    private final Posts posts;

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
