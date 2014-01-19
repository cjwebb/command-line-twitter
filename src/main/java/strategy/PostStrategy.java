package strategy;

import clock.SystemClock;
import model.Input;
import model.Output;
import model.User;
import repository.Repository;

import static model.Post.text;
import static model.User.name;

public class PostStrategy implements Strategy {

    private final SystemClock clock;

    public PostStrategy(SystemClock clock) {
        this.clock = clock;
    }

    @Override
    public Output execute(Repository repository, Input input) {
        User u = name(input.getUserName());
        repository.post(u, text(u, input.getActionText(), clock));
        return Output.empty();
    }
}
