package strategy;

import model.Input;
import model.Output;
import repository.Repository;

import static model.User.name;

public class FollowStrategy implements Strategy {

    @Override
    public Output execute(Repository repository, Input input) {
        repository.follow(name(input.getUserName()), name(input.getActionText()));
        return Output.empty();
    }
}
