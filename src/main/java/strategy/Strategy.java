package strategy;

import model.Input;
import model.Output;
import repository.Repository;

public interface Strategy {
    public Output execute(Repository repository, Input input);
}
