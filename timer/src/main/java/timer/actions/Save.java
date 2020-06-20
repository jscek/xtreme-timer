package timer.actions;

import timer.base.TimerApp;

public class Save extends Actions {

    public Save() {
        this.action = this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        app.saveTimerRecords(input[1]);
    }
}
