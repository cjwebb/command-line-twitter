package strategy;

import model.Input;
import model.Output;
import model.Posts;
import repository.Repository;

import static model.User.name;

public class GetPostsStrategy implements Strategy {

    @Override
    public Output execute(Repository repository, Input input) {
        Posts posts = repository.getUserPosts(name(input.getUserName()));
        return new Output(posts);
    }
}
