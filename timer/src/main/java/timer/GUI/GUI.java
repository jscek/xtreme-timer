package timer.GUI;

import timer.actions.Actions;
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
    public void display(List<TimerRecord> timerRecordList, Actions actionsChain) {
        clearConsole();
        notificationGUI.showNotification(NotifyMode.INFO, "Timer application", "Application has started.");
        TimersGUI timersGUI = new TimersGUI();
        timersGUI.display(timerRecordList, actionsChain);

        String help = actionsChain.generateHelp();
        showHelps(help);
    }

    private void clearConsole() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    private void showHelps(String help){
        System.out.println("\n//////////////////\t\t\tAvailable Commands\t\t\t//////////////////");
        String[] helps = help.split("\\|");
        StringBuilder line= new StringBuilder();
        for (int i = 0; i < helps.length; i++){
            line.append(helps[i]).append("\t\t");
            if (i % 4 == 3){
                System.out.println(line.toString());
                line = new StringBuilder();
            }
        }
        System.out.println(line.toString());
    }
}
