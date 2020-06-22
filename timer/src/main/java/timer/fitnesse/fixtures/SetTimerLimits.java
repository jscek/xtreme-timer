package timer.fitnesse.fixtures;

import timer.GUI.GUI;
import timer.base.TimerCommandLineApp;
import timer.fitnesse.StaticTimerApp;

public class SetTimerLimits {


	private TimerCommandLineApp timerApp;

	private GUI gui = new GUI();


	public void setProjectLimit(String limit) {
		timerApp = StaticTimerApp.app;
		timerApp.createTimer("default");
		String[] input2 = {"setLimit", String.valueOf(timerApp.getTimerRecords().size()), limit};
		timerApp.actionChain.exec(timerApp, input2);
	}

	public String projectLimit() {
		return gui.displayDuration(timerApp.getTimerRecords().get(timerApp.getTimerRecords().size() - 1).getLimit().getSeconds());
	}
}
