package ec.edu.uce.jakarta.payments.services;

import ec.edu.uce.jakarta.payments.model.Payment;
import ec.edu.uce.jakarta.payments.model.Record;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class PaymentServices {

    @Inject
    private Payment payment;

    public String processPayment(Record record, String to, String message) {
        return payment.processPayment(record.getPaymentType(),record.getAccount().getClient().getName(), to, message);
    }
}
