package model;

/**
 *
 */
public class Input {
    private String userName;
    private Action action;
    private String actionText;

    public Input(String userName, Action action, String actionText){
        this.userName = userName;
        this.action = action;
        this.actionText = actionText;
    }

    public String getUserName() {
        return userName;
    }

    public Action getAction() {
        return action;
    }

    public String getActionText() {
        return actionText;
    }
}
