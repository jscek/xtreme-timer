package timer.actions;

import timer.base.TimerApp;

public class NotExist extends Actions {

    public NotExist() {
        this.action = this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        System.out.println("\nAction does not exist: " + input[0] + "\n");
    }
}
