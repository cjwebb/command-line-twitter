import model.Input;
import model.Output;
import tokenizer.Tokenizer;

/**
 *
 */
public class Twitter {

    private Tokenizer tokenizer;
    private StrategyExecutor strategyExecutor;
    private Formatter formatter;

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
