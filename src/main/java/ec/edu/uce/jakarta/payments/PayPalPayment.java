package ec.edu.uce.jakarta.payments;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("paypal")
public class PayPalPayment implements IPay {

	@Override
	public String pay(String from, String to, String message) {
		return String.format("Pago con Pay-Pal.\nFrom: %s\nTo: %s\nMessage: %s", from, to, message);
	}

}
