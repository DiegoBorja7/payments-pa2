package ec.edu.uce.jakarta.payments.model;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("CREDIT_CARD")
public class CreditCardPayment implements IPay {

	@Override
	public String pay(String from, String to, String message) {
		return String.format("Pago con tarjeta de credito.\nFrom: %s\nTo: %s\nMessage: %s", from, to, message);
	}
}
