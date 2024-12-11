package ec.edu.uce.jakarta.payments.classes;

import ec.edu.uce.jakarta.payments.IPay;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date paymentDate;
    private String paymentType; //esta variable deberia enlazarse con los tipos de pagos ya implementados
    private String description;
    private String paymentStatus; // Enum sugerido: PENDIENTE, COMPLETADO, FALLIDO
    //private List <Product> products;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "account_number_account")
    private Account account;

    public Record() {
    }

    public Record(Date paymentDate, String paymentType, String description, String paymentStatus, Double amount, Account account) {
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.description = description;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.account = account;
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
                ", paymentType='" + paymentType + '\'' +
                ", description='" + description + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", amount=" + amount +
                ", account=" + account +
                '}';
    }
}
