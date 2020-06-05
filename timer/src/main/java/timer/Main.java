package timer;


import timer.base.TimerApp;

public class Main {
	public static void main(String[] args) {
		TimerApp timerApp = new TimerApp();
		timerApp.start();
		Runtime.getRuntime().exit(0);
	}
}
