package ec.edu.uce.jakarta.payments.model;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("TRANSFER")
public class TransferPayment implements IPay {

	@Override
	public String pay(String from, String to, String message) {
		return String.format("Pago con Transferencia.\nFrom: %s\nTo: %s\nMessage: %s", from, to, message);
	}

}
