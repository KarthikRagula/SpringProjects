//import org.example.config.AppConfig;
import org.example.config.AppConfig;
import org.example.entity.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        XML configuration
//        ApplicationContext context= new ClassPathXmlApplicationContext("beans.xml");
//        Employee employeeBean=(Employee)context.getBean("EmployeeBean");
//        System.out.println(employeeBean);

//        Java based configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Employee employeeBean = context.getBean(Employee.class);
        employeeBean.setEmpId(1);
        employeeBean.setEmpName("Karthik");
        employeeBean.setPhone("8008041620");
        System.out.println(employeeBean);
    }
}
