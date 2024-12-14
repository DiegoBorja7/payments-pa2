package ec.edu.uce.jakarta.payments.model;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("PAYPAL")
public class PayPalPayment implements IPay {

	@Override
	public String pay(String from, String to, String message) {
		return String.format("Pago con Pay-Pal.\nFrom: %s\nTo: %s\nMessage: %s", from, to, message);
	}

}
