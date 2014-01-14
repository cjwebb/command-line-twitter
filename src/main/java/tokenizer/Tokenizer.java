package tokenizer;

import model.Input;

public interface Tokenizer {
    public Input split(String str);
}
