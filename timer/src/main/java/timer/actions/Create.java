package timer.actions;

import timer.base.TimerApp;

public class Create extends Actions {

    public Create() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help = "Create {project name}";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        app.createTimer(getProjectName(input));
    }

    private String getProjectName(String[] input) {
        String projectName = "";
        if (input.length >= 2) {
            input[0] = "";
            projectName = String.join(" ", input).substring(1);
        }
        return projectName;
    }
}
