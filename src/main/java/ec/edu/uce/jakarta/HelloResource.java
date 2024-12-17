package ec.edu.uce.jakarta;

import ec.edu.uce.jakarta.notifications.jpa.*;
import ec.edu.uce.jakarta.payments.model.*;
import ec.edu.uce.jakarta.payments.model.Record;
import ec.edu.uce.jakarta.payments.services.*;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@Path("/application")
public class HelloResource {
    @Inject
    private AccountServices accountServices;

    @Inject
    private RecordServices recordServices;

    @Inject
    private PaymentServices paymentServices;

    @Inject
    private BankServices bankServices;

    @Inject
    private ProductServices productServices;

    @Inject
    private UserServices userServices;

    // Usuario
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/payment/create-user")
    public Response createUser(User user) {
        userServices.createUser(user);

        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    @Path("/payment/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        User user = userServices.findByIDUser(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }

        return Response.ok(user).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("payment/get-all-users")
    public Response getAllUsers() {
        List<User> users = userServices.getAllUsers();
        if (users.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(users).build();
    }

    @PUT
    @Path("/payment/user/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, User user) {
        if (userServices.findByIDUser(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        user.setId(id);
        userServices.updateUser(user);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/payment/user/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        User user = userServices.findByIDUser(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        userServices.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // Banco
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/payment/create-bank")
    public Response createBank(Bank bank) {
        try {
            bankServices.createBank(bank);
            return Response.status(Response.Status.CREATED).entity(bank).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Bank not create").build();
        }
    }

    @GET
    @Path("/payment/bank/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBankById(@PathParam("id") int id) {
        Bank bank = bankServices.findByIDBank(id);

        if (bank != null) {
            return Response.ok(bank).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Bank not found").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("payment/get-all-banks")
    public Response getAllBanks() {
        List<Bank> banks = bankServices.getAllBanks();
        if (banks.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(banks).build();
    }

    @PUT
    @Path("/payment/bank/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBank(@PathParam("id") int id, Bank updatedBank) {
        Bank bank = bankServices.findByIDBank(id);

        if (bank != null) {
            updatedBank.setId(id);
            bankServices.updateBank(updatedBank);
            return Response.ok(updatedBank).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Bank not found").build();
        }
    }

    @DELETE
    @Path("/payment/bank/{id}")
    public Response deleteBank(@PathParam("id") int id) {
        Bank bank = bankServices.findByIDBank(id);
        if (bank != null) {
            bankServices.deleteBank(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Bank not found").build();
        }
    }

    // Cuentas
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/payment/create-account")
    public Response createAccount(Account account) {
        Bank b = bankServices.findByIDBank(1);
        User u = userServices.findByIDUser(1);

        account.setBank(b);
        account.setClient(u);
        try {
            accountServices.createAccount(account);
            return Response.status(Response.Status.CREATED).entity(account).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("account not create").build();
        }
    }

    @GET
    @Path("/payment/account/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountById(@PathParam("id") int id) {
        Account account = accountServices.findByIDAccount(id);
        if (account != null) {
            return Response.ok(account).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("account not found").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("payment/get-all-accounts")
    public Response getAllAccounts() {
        List<Account> account = accountServices.getAllAccounts();
        if (account.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(account).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/payment/account/{id}")
    public Response updateAccount(@PathParam("id") int id, Account account) {
        Account existingAccount = accountServices.findByIDAccount(id);
        if (existingAccount != null) {
            account.setNumberAccount(id);
            account.setBank(existingAccount.getBank());
            account.setClient(existingAccount.getClient());
            accountServices.updateAccount(account);
            return Response.ok(account).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("account not found").build();
        }
    }

    @DELETE
    @Path("/payment/account/{id}")
    public Response deleteAccount(@PathParam("id") int id) {
        Account existingAccount = accountServices.findByIDAccount(id);
        if (existingAccount != null) {
            accountServices.deleteAccount(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("account not found").build();
        }
    }

    // Record
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/payment/create-record")
    public Record createRecord(Record record) {
        Account a = new Account();

        a = accountServices.findByIDAccount(1000);

        //obtenemos la fecha actual al momento de realizar una transaccion
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        record.setPaymentDate(date);
        record.setAccount(a);

        recordServices.createRecord(record);

        return record;
    }

    @GET
    @Produces("text/plain")
    @Path("/payment")
    public String payments() {
        Product p = new Product();
        Record r = new Record();


        //productServices.createProduct(new Product("parlante", "audio", 100.5, 3));
        p = productServices.findByIDProduct(1);
        //productServices.deleteProduct(2);

        //recordServices.createRecord(new Record(date,PaymentType.CREDIT_CARD,"pago test",PaymentStatus.COMPLETADO,100011.10,a));
        r = recordServices.findByIDRecord(12);

        return r.toString();
    }
}