package ec.edu.uce.jakarta.payments.run;

import ec.edu.uce.jakarta.notifications.jpa.Message;
import ec.edu.uce.jakarta.notifications.jpa.MessageServices;
import ec.edu.uce.jakarta.notifications.jpa.Student;
import ec.edu.uce.jakarta.notifications.jpa.StudentServices;
import ec.edu.uce.jakarta.payments.IPay;
import ec.edu.uce.jakarta.payments.QualifierPayment;
import ec.edu.uce.jakarta.payments.classes.User;
import ec.edu.uce.jakarta.payments.services.UserServices;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;


@Path("/hello-world")
public class HelloResource {

    @Inject
    @QualifierPayment("creditcard")
    IPay creditCard;

    @Inject
    @QualifierPayment("paypal")
    IPay paypal;

    @Inject
    @QualifierPayment("transfer")
    IPay transfer;

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Produces("text/plain")
    @Path("/credit-card")
    public String creditCardPayment() {

        return creditCard.pay("diego.borja", "@uce.edu.ec", "Exitoso");
    }

    @GET
    @Produces("text/plain")
    @Path("/pay-pal")
    public String paypalPayment() {

        return paypal.pay("diego.borja", "@uce.edu.ec", "Exitoso");
    }

    @GET
    @Produces("text/plain")
    @Path("/transfer")
    public String transferPayment() {

        return transfer.pay("diego.borja", "@uce.edu.ec", "Exitoso");
    }

    @GET
    @Produces("text/plain")
    @Path("/jpa")
    public String jpaServices() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("UnitPersistenceDB");
        EntityManager entityManage = entityManagerFactory.createEntityManager();

        StudentServices student = new StudentServices(entityManage);
        student.createStudent(new Student(4,"lis"));

        MessageServices message = new MessageServices(entityManage);
        message.createMessge(new Message("mensaje de prueba."));

        return "Creacion exitosa.";
    }

    @GET
    @Produces("text/plain")
    @Path("/payment")
    public String payments() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("UnitPersistencePaymentsDB");
        EntityManager entityManage = entityManagerFactory.createEntityManager();

        User u = new User();
        UserServices userServices = new UserServices(entityManage);

        //userServices.createUser(new User("glis", "quito", "0963", "@uce.edu"));

        u = userServices.findByID(3);

        /*u.setName("test");
        u.setEmail("@espe");
        userServices.update(u);*/

        userServices.delete(4);

        return u.toString();
    }
}