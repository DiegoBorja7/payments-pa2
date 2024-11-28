package ec.edu.uce.jakarta.payments.classes;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Records {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
