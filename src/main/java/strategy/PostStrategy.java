package strategy;

import model.Input;
import model.Output;
import model.User;
import repository.Repository;

import static model.Post.text;
import static model.User.name;

public class PostStrategy implements Strategy {

    @Override
    public Output execute(Repository repository, Input input) {

        User u = name(input.getUserName());
        repository.post(u, text(u, input.getActionText()));
        return Output.empty();
    }
}
