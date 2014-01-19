import formatter.Formatter;
import model.Input;
import model.Output;
import strategy.StrategyExecutor;
import tokenizer.Tokenizer;

public class Twitter {

    private final Tokenizer tokenizer;
    private final StrategyExecutor strategyExecutor;
    private final Formatter formatter;

    public Twitter(Tokenizer tokenizer, StrategyExecutor strategyExecutor, Formatter formatter) {
        this.tokenizer = tokenizer;
        this.strategyExecutor = strategyExecutor;
        this.formatter = formatter;
    }

    public String something(String command) {
        Input c = tokenizer.split(command);
        Output o = strategyExecutor.executeStrategy(c);
        return formatter.format(o);
    }
}
