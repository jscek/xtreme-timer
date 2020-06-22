package timer.actions;

import timer.base.TimerApp;

public class Read extends Actions {

    public Read() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help="Read {filename.ext}";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        app.loadTimerRecords(input[1]);
    }
}
