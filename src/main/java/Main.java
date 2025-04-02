//import org.example.config.AppConfig;

import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        XML configuration
//        ClassPathXmlApplicationContext context= new ClassPathXmlApplicationContext("beans.xml");
//        Employee employeeBean=context.getBean("EmployeeBean",Employee.class);
//        System.out.println(employeeBean);

//        Java based configuration
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        Employee employeeBean = context.getBean(Employee.class);
//        System.out.println(employeeBean);

//        context.close();
//        Employee emp = new Employee(4, "Karthik", 20, "8008041620");
//
//        SessionFactory factory = new Configuration()
//                .addAnnotatedClass(org.example.entity.Employee.class)
//                .configure()
//                .buildSessionFactory();
//
//        Session session = factory.openSession();
//
//        Transaction transaction = session.beginTransaction();
//
//        session.persist(emp);
//        transaction.commit();
//
//        session.close();
//        factory.close();
    }
}
