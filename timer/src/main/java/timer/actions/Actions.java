package timer.actions;

import timer.base.TimerApp;

import java.util.List;

public abstract class Actions {

    protected String action;
    protected Actions nextAction;

    public void setNextAction(Actions nextAction) {
        this.nextAction = nextAction;
    }

    public Actions chainActions(List<Actions> actions) {

        for (int i = 0; i < actions.size() - 1; i++) {
            actions.get(i).setNextAction(actions.get(i + 1));
        }
        return actions.get(0);
    }

    public void exec(TimerApp app, String[] input) {
        if (this.action.toLowerCase().equals(input[0].toLowerCase())) {
            this.perform(input, app);
        } else {
            if (nextAction != null) {
                nextAction.exec(app, input);
            } else {
                Actions notExist = new NotExist();
                notExist.perform(input, app);
            }
        }
    }

    abstract protected void perform(String[] input, TimerApp app);
}