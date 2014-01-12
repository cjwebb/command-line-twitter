package model;

import java.util.Date;

/**
 *
 */
public class Post implements Comparable<Post> {

    private final String text;
    private Date date;

    private Post(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    public static Post text(String name) {
        return new Post(name, new Date()); // todo: extract to a factory method, so we can inject a system clock
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text + " | " + date.toString();
    }

    @Override
    public int compareTo(Post other) {
        return other.date.compareTo(this.date);
    }
}
