package timer.actions;

import timer.base.TimerApp;
import timer.utils.TimeInputParser;

public class SetLimit extends Actions {

    public SetLimit() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help = "SetLimit {id} {<xx>h<xx>m<xx>s or hh:mm:ss e.g 8h12m34s or 8:12:34}";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        app.setLimit(Long.parseLong(input[1]), TimeInputParser.parseInput(input));
    }
}
