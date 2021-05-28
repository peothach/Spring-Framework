package beanscope.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyMain {
    public static void main(String[] args) {
        /**
         * ApplicationContext with Annotation @Component & @ComponentScan
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(MyAppConfig.class);
        SMSService smsService = context.getBean("smsService", SMSService.class);
        smsService.sendMessage("test@gmail.com", "Hi");

        /**
         * ApplicationContext with Annotation XML Application context.
         */
        ApplicationContext context1 = new ClassPathXmlApplicationContext("spring-annotation.xml");
        SMSService smsService1 = context1.getBean("smsService", SMSService.class);
        smsService1.sendMessage("test1@gmail.com", "Hi");
    }
}
