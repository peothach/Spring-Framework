package lifecycle.creation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-lifecycle.xml");
        String firstName = context.getBean("customer", Customer.class).getFirstName();
        System.out.println("First Name: " + firstName);
    }
}
