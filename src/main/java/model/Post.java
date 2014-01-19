package model;

import clock.SystemClock;

import java.util.Date;

public final class Post {

    private final User user;
    private final String text;
    private final Date date;

    private Post(User user, String text, Date date) {
        this.user = user;
        this.text = text;
        this.date = date;
    }

    public static Post text(User user, String text, SystemClock clock) {
        return new Post(user, text, clock.getTime());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (date != null ? !date.equals(post.date) : post.date != null) return false;
        if (text != null ? !text.equals(post.text) : post.text != null) return false;
        if (user != null ? !user.equals(post.user) : post.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
