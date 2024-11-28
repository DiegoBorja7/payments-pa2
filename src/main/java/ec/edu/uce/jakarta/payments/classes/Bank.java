package ec.edu.uce.jakarta.payments.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Bank {
    @Id
    private int id;
    private String name;

    public Bank() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
