package timer.fitnesse.fixtures;

import timer.base.TimerApp;
import timer.fitnesse.StaticTimerApp;

public class AddTimerNames {

	private TimerApp timerApp;

	public void setProjectName(String projectName) {
		timerApp = StaticTimerApp.app;

		String[] input = {"create", projectName};
		timerApp.addTimer(input);
	}

	public String projectName() {
		return timerApp.getTimerRecords().get(timerApp.getTimerRecords().size()-1).getProjectName();
	}
}
