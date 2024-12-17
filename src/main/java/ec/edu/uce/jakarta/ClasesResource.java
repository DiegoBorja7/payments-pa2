package ec.edu.uce.jakarta;

import ec.edu.uce.jakarta.notifications.jpa.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;
import java.util.Random;

@Path("/clases")
public class ClasesResource {
    private StringBuilder text;

    @Inject
    private EmployeeServices employeeServices;

    @Inject
    private AddressServices addressServices;

    @Inject
    private StudentServices studentServices;

    @Inject
    private CourseService coursesService;

    /// Endpoints para las clases de ejemplo realizados en el aula

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
    @Path("/saveEmployee")
    public String saveEmployee(@QueryParam("name") String name) {
        Employee e = new Employee();
        Address a = new Address();

        Random random = new Random();

        e.setName(name);
        e.setAge(random.nextInt(60) + 5);
        a = addressServices.findByIDAddress(7);
        e.setAddress(a);

        employeeServices.save(e);

        return "Se ha generado el empleado: " + name ;
    }

    @GET
    @Produces("text/plain")
    @Path("/age")
    public String age(@QueryParam("age") int age) {
        text = new StringBuilder();
        List<Employee> employees = employeeServices.getAllEmployeesByAge(age);
        for (Employee e : employees) {
            text.append(e.getName()).append(" - ").append(e.getAge()).append("\n");
        }

        return text.toString();
    }


    @GET
    @Produces("text/plain")
    @Path("/test")
    public String test() {
        Student s = new Student();
        Course c = new Course();

//        s.setId(4);
//        s.setName("liz");
//        studentServices.createStudent(s);
        s = studentServices.findByID(4);

//        c.setName("matematicas");
//        c.setDescription("2do semestre");
//        coursesService.createCourse(c);

        //CREAR UN JOIN CON UN LISTADO DE ESTUDIANTES PERTENECIENTE A 1 CURSO

        return s.toString();
    }
}
