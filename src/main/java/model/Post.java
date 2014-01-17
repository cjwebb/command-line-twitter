package model;

import java.util.Date;

public class Post {

    private User user;
    private final String text;
    private Date date;

    private Post(User user, String text, Date date) {
        this.user = user;
        this.text = text;
        this.date = date;
    }

    public static Post text(User user, String text) {
        return new Post(user, text, new Date()); // todo: extract to a factory method, so we can inject a system clock
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.getName() + " - " + text + " (" + date.toString() + ")";
    }

}
