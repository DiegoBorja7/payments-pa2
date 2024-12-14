package ec.edu.uce.jakarta.payments.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private String description;
    private String paymentStatus; // Enum sugerido: PENDIENTE, COMPLETADO, FALLIDO
    //private List <Product> products;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "account_number_account")
    private Account account;

    public Record() {
    }

    public Record(Date paymentDate, PaymentType paymentType, String description, String paymentStatus, Double amount, Account account) {
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.description = description;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.account = account;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", paymentType=" + paymentType +
                ", description='" + description + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", amount=" + amount +
                ", account=" + account +
                '}';
    }
}
