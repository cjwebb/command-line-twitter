package model;

public final class Input {
    private final String userName;
    private final Action action;
    private final String actionText;

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
