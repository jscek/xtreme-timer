package timer.actions;

import timer.base.TimerApp;

public class Refresh extends Actions {

    public Refresh() {
        this.action = this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        app.addTimer(input);
    }
}
