import model.Command;
import model.Output;
import tokenizer.Tokenizer;

/**
 *
 */
public class Twitter {

    private Tokenizer tokenizer;
    private Logic logic;
    private Formatter formatter;

    public Twitter(Tokenizer tokenizer, Logic logic, Formatter formatter) {
        this.tokenizer = tokenizer;
        this.logic = logic;
        this.formatter = formatter;
    }

    public String something(String command) {
        Command c = tokenizer.split(command);
        Output o = logic.doStuff(c);
        return formatter.format(o);
    }
}
