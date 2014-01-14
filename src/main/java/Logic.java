import model.Command;
import model.Output;
import model.Posts;
import repository.Repository;

import static model.Post.text;
import static model.User.name;

/**
 *
 */
public class Logic {
    private Repository repo;

    public Logic(Repository repo) {

        this.repo = repo;
    }

    public Output doStuff(Command command) {

        if (command.getAction().equals("POST")) {
            repo.post(name(command.getUserName()), text(command.getActionText()));
            return Output.empty();
        } else if (command.getAction().equals("USER_FEED")) {
            Posts posts = repo.getUserPosts(name(command.getUserName()));
            return new Output(posts);
        } else if (command.getAction().equals("WALL")) {
            Posts posts = repo.getUserWall(name(command.getUserName()));
            return new Output(posts);
        } else {
            repo.follow(name(command.getUserName()), name(command.getActionText()));
            return Output.empty();
        }

    }
}
