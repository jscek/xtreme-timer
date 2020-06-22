package timer.fitnesse.fixtures;

import timer.base.TimerCommandLineApp;
import timer.fitnesse.StaticTimerApp;

public class AddTimerNames {

	private TimerCommandLineApp timerApp;

	public void setProjectName(String projectName) {
		timerApp = StaticTimerApp.app;

		timerApp.createTimer(projectName);
	}

	public String projectName() {
		return timerApp.getTimerRecords().get(timerApp.getTimerRecords().size()-1).getProjectName();
	}
}
