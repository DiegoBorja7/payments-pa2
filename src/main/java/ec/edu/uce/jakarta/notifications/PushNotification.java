package ec.edu.uce.jakarta.notifications;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PushNotification implements Notifiable {

	@Override
	public String notifyMessage() {
		return "Tienes una notificacion mediante una alerta.";
	}

}
