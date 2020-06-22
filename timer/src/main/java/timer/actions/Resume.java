package timer.actions;

import timer.base.TimerApp;

public class Resume extends Actions {

    public Resume() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help = "Resume {id}";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        app.resumeTimer(Long.parseLong(input[1]));
    }
}
