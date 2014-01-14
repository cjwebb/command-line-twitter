package tokenizer;

import model.Action;
import model.Input;

public class TwitterTokenizer implements Tokenizer {

    public Input split(String str) {

        String name = substr(str, 0, indexOfNextSpace(str));

        String rest = drop(str, name.length()).trim();
        String action = substr(rest, 0, indexOfNextSpace(rest));

        String extra = drop(rest, action.length()).trim();

        return new Input(name.trim(), action(action), extra);
    }

    /* substring without the exception */
    private String substr(String str, int begin, int end) {
        if (end == -1){
            return str.substring(begin, str.length());
        } else return str.substring(begin, end);
    }

    private int indexOfNextSpace(String str) {
        return str.indexOf(" ");
    }

    private String drop(String str, int n) {
        return str.substring(n, str.length());
    }

    private Action action(String str) {
        if (str.equals("->")) {
            return Action.POST;
        } else if (str.equals("follows")) {
            return Action.FOLLOW;
        } else if (str.equals("wall")) {
            return Action.GET_WALL;
        } else {
            return Action.GET_POSTS;
        }
    }


}
