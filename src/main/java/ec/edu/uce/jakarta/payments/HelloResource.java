package ec.edu.uce.jakarta.payments;

import ec.edu.uce.jakarta.notifications.jpa.*;
import ec.edu.uce.jakarta.payments.model.*;
import ec.edu.uce.jakarta.payments.model.Record;
import ec.edu.uce.jakarta.payments.services.*;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@Path("/hello-world")
public class HelloResource {
    private StringBuilder text;

    @Inject
    EmployeeServices employeeServices;

    @Inject
    AddressServices addressServices;

    /// ////

    @Inject
    AccountServices accountServices;

    @Inject
    RecordServices recordServices;

    @Inject
    PaymentServices paymentServices;

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Produces("text/plain")
    @Path("/credit-card")
    public String creditCardPayment() {

        return "";//creditCard.pay("diego.borja", "@uce.edu.ec", "Exitoso");
    }

    @GET
    @Produces("text/plain")
    @Path("/pay-pal")
    public String paypalPayment() {

        return "";//paypal.pay("diego.borja", "@uce.edu.ec", "Exitoso");
    }

    @GET
    @Produces("text/plain")
    @Path("/transfer")
    public String transferPayment() {

        return "";//transfer.pay("diego.borja", "@uce.edu.ec", "Exitoso");
    }

    @GET
    @Produces("text/plain")
    @Path("/get/{id}")
    public String getEmployee(@PathParam("id") int id) {
        Employee e = employeeServices.findByIDEmployee(id);

        if(e == null)
            return "No existe el empleado";
        else
            return "Hola " + e.getName();
    }

    @GET
    @Produces("text/plain")
    @Path("/getallemployees")
    public String getAllEmployee() {
        text = new StringBuilder();
        List<Employee> allEmployees = employeeServices.getAllEmployees();

        text.append("Empleados >> \n");
        for(Employee e:allEmployees){
            text.append(e.getName()).append("\n");
        }

        return text.toString();
    }

    @GET
    @Produces("text/plain")
    @Path("/getallemployeesandaddress")
    public String getAllEmployeeandAddress() {
        text = new StringBuilder();
        List<Employee> allEmployeesandAddress = employeeServices.getAllEmployeesWithAddress();

        text.append("Empleados y Direccion>> \n");
        for(Employee e:allEmployeesandAddress){
            text.append(e.getName()).append(" - ");
            text.append(e.getAddress().getStreet()).append("\n");
        }

        return text.toString();
    }

    @GET
    @Produces("text/plain")
    @Path("/payment")
    public String payments() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("UnitPersistencePaymentsDB");
        EntityManager entityManage = entityManagerFactory.createEntityManager();

        User u = new User();
        Product p = new Product();
        Bank b = new Bank();
        Account a = new Account();
        Record r = new Record();

        UserServices userServices = new UserServices(entityManage);
        ProductServices productServices = new ProductServices(entityManage);
        BankServices bankServices = new BankServices(entityManage);

        //userServices.createUser(new User("glis", "quito", "0963", "@uce.edu"));

        u = userServices.findByIDUser(3);

        /*u.setName("test");
        u.setEmail("@espe");
        userServices.updateUser(u);*/

        //userServices.deleteUser(4);

        //productServices.createProduct(new Product("parlante", "audio", 100.5, 3));
        p = productServices.findByIDProduct(1);
        //productServices.deleteProduct(2);

        //bankServices.createBank(new Bank("Pichincha"));
        b = bankServices.findByIDBank(0);

        //accountServices.createAccount(new Account(10.5,b,u));
        a = accountServices.findByIDAccount(1000);

        //obtenemos la fecha actual al momento de realizar una transaccion
        LocalDateTime currentDateTime = LocalDateTime.now();
        java.util.Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());

        //recordServices.createRecord(new Record(date,PaymentType.CREDIT_CARD,"pago cc","check",1000.10,a));
        r = recordServices.findByIDRecord(11);

        return paymentServices.processPayment(r,"uce.edu.ec", "funciona");
    }
}