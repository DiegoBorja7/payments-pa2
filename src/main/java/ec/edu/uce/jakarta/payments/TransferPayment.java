package ec.edu.uce.jakarta.payments;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("transfer")
public class TransferPayment implements IPay {

	@Override
	public String pay(String from, String to, String message) {
		return String.format("Pago con Transferencia.\nFrom: %s\nTo: %s\nMessage: %s", from, to, message);
	}

}
