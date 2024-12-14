package ec.edu.uce.jakarta.payments.model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Payment {

    @Inject
    @QualifierPayment("CREDIT_CARD")
    private IPay creditCardPayment;

    @Inject
    @QualifierPayment("TRANSFER")
    private IPay transferPayment;

    @Inject
    @QualifierPayment("PAYPAL")
    private IPay payPalPayment;

    public String processPayment(PaymentType paymentType, String from, String to, String message) {
        IPay paymentMethod = null;
        switch (paymentType) {
            case CREDIT_CARD:
                paymentMethod = creditCardPayment;
                break;
            case TRANSFER:
                paymentMethod = transferPayment;
                break;
            case PAYPAL:
                paymentMethod = payPalPayment;
                break;
            default:
                throw new IllegalArgumentException("Método de pago no soportado: " + paymentType);
        }

        return paymentMethod.pay(from, to, message);
    }
}
