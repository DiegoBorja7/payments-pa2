package ec.edu.uce.jakarta.payments.classes;

import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_sequence", initialValue = 1000, allocationSize = 1)
    private int numberAccount;
    private Double balance;

    ///implementar una clase tarjeta para el uso de tarjeta de debito / credito
    /// si tiene tarjetas de hace el pago con tarjeta de credito
    /// caso contrario solo paypal y transferencia porque tiene una de debito

    @OneToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client ;

    public Account() {
    }

    public int getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(int numberAccount) {
        this.numberAccount = numberAccount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
