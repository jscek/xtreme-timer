package timer.actions;

import timer.base.TimerApp;

public class Stop extends Actions {

    public Stop() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help = "Stop {id}";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        try {
            long id = Long.parseLong(input[1]);
            app.stopTimer(id);
        } catch (Exception e) {
            //Do nothing
        }
    }
}
