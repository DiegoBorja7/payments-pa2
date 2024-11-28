package ec.edu.uce.jakarta.notifications;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationManager {

	public String sendNotification(Notifiable notify) {
		return notify.notifyMessage();
	}
}
