package ec.edu.uce.jakarta.notifications.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

///POJO Plain old Java Object
@Entity
public class Student {
    @Id
    private int id;
    private String name;

    public Student() {}

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
