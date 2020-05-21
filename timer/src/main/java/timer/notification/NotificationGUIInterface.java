package timer.notification;

import timer.enums.NotifyMode;

public interface NotificationGUIInterface {
	void showNotification(NotifyMode mode, String title, String msg);
}
