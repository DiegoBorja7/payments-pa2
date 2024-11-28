package ec.edu.uce.jakarta.notifications;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailNotification implements Notifiable {

	@Override
	public String notifyMessage() {
		return "Tienes una notificacion por E-mail.";

	}
}
