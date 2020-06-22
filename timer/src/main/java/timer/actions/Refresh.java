package timer.actions;

import timer.base.TimerApp;

public class Refresh extends Actions {

    public Refresh() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help = "Refresh";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {

    }
}
