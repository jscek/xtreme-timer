package timer.notification;

import java.awt.*;

import timer.enums.NotifyMode;

public class NotificationGUI implements NotificationGUIInterface {
	public void showNotification(NotifyMode mode, String title, String msg) {
		try {
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().createImage("some-icon.png");

			TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
			trayIcon.setImageAutoSize(true);
			trayIcon.setToolTip("System tray icon demo");
			tray.add(trayIcon);

			switch (mode) {
			case ERROR:
				trayIcon.displayMessage(title, msg, TrayIcon.MessageType.ERROR);
				break;
			case INFO:
				trayIcon.displayMessage(title, msg, TrayIcon.MessageType.INFO);
				break;
			case WARNING:
				trayIcon.displayMessage(title, msg, TrayIcon.MessageType.WARNING);
				break;
			}

		} catch (Exception ex) {
			System.err.print(ex);
		}
	}
}
