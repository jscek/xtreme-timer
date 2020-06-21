package timer.GUI;

import timer.base.TimerRecord;
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
        clearConsole();
        notificationGUI.showNotification(NotifyMode.INFO, "Timer application", "Application has started.");
        TimersGUI timersGUI = new TimersGUI();
        timersGUI.display(timerRecordList);
        System.out.println("\n\nAvailable Commands:\n");
        System.out.println("Create {project}\t\tStart {id}\t\tStop {id}\t\tResume {id}\t\tSave {filename}\t\tRead {filename}\t\tRefresh\t\tQuit\t\t");
        System.out.println("Report {from:yyyy-mm-dd} {to:yyyy-mm-dd}\t\tSetLimit {id} {duration[s]}\t\t");
        System.out.println("SendEmail {receiver} {\"subject\"} {\"text\"} {reportFile<.csv>} ");
    }

    private void clearConsole() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
