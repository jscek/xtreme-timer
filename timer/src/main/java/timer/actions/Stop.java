package timer.actions;

import timer.base.TimerApp;

public class Stop extends Actions {

    public Stop() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help = "Stop {id}";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        app.stopTimer(Long.parseLong(input[1]));
    }
}
