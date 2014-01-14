import model.Action;
import model.Input;
import model.Output;
import repository.Repository;
import strategy.Strategy;

import java.util.Map;

public class StrategyExecutor {
    private Repository repository;
    private Map<Action, Strategy> strategyMap;

    public StrategyExecutor(Repository repository, Map<Action, Strategy> strategyMap) {
        this.repository = repository;
        this.strategyMap = strategyMap;
    }

    public Output executeStrategy(Input input) {
        Strategy strategy = strategyMap.get(input.getAction());
        if (strategy != null){
            return strategy.execute(repository, input);
        } else {
            return Output.empty();
        }
    }
}
