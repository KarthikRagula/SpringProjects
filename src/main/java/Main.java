//import org.example.config.AppConfig;
import org.example.entity.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        XML configuration
        ClassPathXmlApplicationContext context= new ClassPathXmlApplicationContext("beans.xml");
        Employee employeeBean=context.getBean("EmployeeBean",Employee.class);
        System.out.println(employeeBean);

//        Java based configuration
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        Employee employeeBean = context.getBean(Employee.class);
//        System.out.println(employeeBean);

        context.close();
    }
}
