package ec.edu.uce.jakarta.notifications;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;


@WebServlet(name = "notificationServlet", value = "/notification-servlet")
public class MyServletApp extends HttpServlet {

	@Inject
	private NotificationManager notification;
	@Inject
	private EmailNotification email;
	@Inject
	private SMSNotification sms;
	@Inject
	private PushNotification push;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(notification.sendNotification(email));
		System.out.println(notification.sendNotification(sms));
		System.out.println(notification.sendNotification(push));
		
		resp.getWriter().println(notification.sendNotification(email));
		resp.getWriter().println(notification.sendNotification(sms));
		resp.getWriter().println(notification.sendNotification(push));
		
		String message = "Acción realizada correctamente !";
		String htmlResponse = "<html>"
	            + "<head><title>Notificación</title></head>"
	            + "<body>"
	            + "<script type=\"text/javascript\">"
	            + "alert('" + message + "');"
	            + "</script>"
	            + "</body>"
	            + "</html>";
		
		resp.setContentType("text/html");
        resp.getWriter().write(htmlResponse);
		
	}

}
