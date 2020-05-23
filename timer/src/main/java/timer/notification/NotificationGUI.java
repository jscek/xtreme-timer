package timer.notification;

import java.awt.*;

import timer.enums.NotifyMode;

public class NotificationGUI implements NotificationGUIInterface {
	private SystemTray tray;
	private Image image;

	public NotificationGUI() {
		tray = SystemTray.getSystemTray();
		image = Toolkit.getDefaultToolkit().createImage("some-icon.png");
	}

	public void showNotification(NotifyMode mode, String title, String msg) {
		try {
			TrayIcon trayIcon = createTrayIcon();
			tray.add(trayIcon);
			displayMessage(mode, title, msg, trayIcon);
		} catch (Exception ex) {
			System.err.println("Unsupported desktop environment.");
		}
	}

	private TrayIcon createTrayIcon() {
		TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
		trayIcon.setImageAutoSize(true);
		trayIcon.setToolTip("System tray icon demo");
		return trayIcon;
	}

	private void displayMessage(NotifyMode mode, String title, String msg, TrayIcon trayIcon) {
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

		}
}
