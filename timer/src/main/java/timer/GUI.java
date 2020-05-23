package timer;

import timer.enums.NotifyMode;
import timer.notification.NotificationGUI;
import timer.notification.NotificationGUIInterface;

import java.util.List;

public class GUI extends TimerGUI {

    NotificationGUIInterface notificationGUI;

    public GUI() {
        super();
        notificationGUI = new NotificationGUI();
    }

    @Override
    public void display(List<TimerRecord> timerRecordList) {
        notificationGUI.showNotification(NotifyMode.INFO, "Timer application", "Application has started.");
        System.out.println("//////////Timers///////////");
        TimersGUI timersGUI = new TimersGUI();
        timersGUI.display(timerRecordList);
        System.out.println("//////////Command///////////");
        System.out.println("Create {project}\t\tStart {id}\t\tStop {id}\t\tResume {id}\t\tSetLimit {id} {duration[s]}\t\tSave {filename}\t\tRead {filename}\t\tReport {start} {stop}}\t\tSendEmail {receiver} {subject} {text}\t\tRefresh\t\tQuit");

    }
}
