package timer.actions;

import timer.base.TimerApp;

public class Start extends Actions {

    public Start() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help = "Start {id}";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        try {
            long id = Long.parseLong(input[1]);
            app.startTimer(id);
        } catch (Exception e) {
            //Do nothing
        }
    }
}
