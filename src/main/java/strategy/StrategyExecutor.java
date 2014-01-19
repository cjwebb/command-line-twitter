package strategy;

import model.Action;
import model.Input;
import model.Output;
import repository.Repository;

import java.util.Map;

public final class StrategyExecutor {
    private final Repository repository;
    private final Map<Action, Strategy> strategyMap;

    public StrategyExecutor(Repository repository, Map<Action, Strategy> strategyMap) {
        this.repository = repository;
        this.strategyMap = strategyMap;
    }

    public Output execute(Input input) {
        Strategy strategy = strategyMap.get(input.getAction());
        if (strategy != null){
            return strategy.execute(repository, input);
        } else {
            return Output.empty();
        }
    }
}
