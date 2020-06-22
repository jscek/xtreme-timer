package timer.actions;

import timer.base.TimerApp;
import timer.utils.TimeInputParser;

public class SetLimit extends Actions {

    public SetLimit() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help = "SetLimit {id} {duration[s]}";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        app.setLimit(Long.parseLong(input[1]), TimeInputParser.parseInput(input));
    }
}
