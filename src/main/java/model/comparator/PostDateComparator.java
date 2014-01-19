package model.comparator;

import model.Post;

import java.util.Comparator;

public class PostDateComparator implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}
