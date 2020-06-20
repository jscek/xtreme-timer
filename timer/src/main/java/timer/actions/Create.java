package timer.actions;

import timer.base.TimerApp;

public class Create extends Actions {

    public Create() {
        this.action = this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        app.addTimer(input);
    }
}
