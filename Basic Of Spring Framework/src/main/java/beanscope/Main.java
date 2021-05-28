package beanscope;

import beanscope.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        /**
         * Singleton
         * Per instance per IoC Container (Application Context).
         * Using XML Application context.
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        EmailService emailService1 = context.getBean("emailService1", EmailService.class);
        EmailService emailService2 = context.getBean("emailService1", EmailService.class);
        System.out.println(emailService1==emailService2);

        /**
         * Prototype
         * Per instance per IoC Container (Application Context).
         * Using XML Application context and Class config and Annotation.
         */
        ApplicationContext context1 = new ClassPathXmlApplicationContext("spring.xml");
        EmailService emailService3 = context1.getBean("prototypeEmailService", EmailService.class);
        EmailService emailService4 = context1.getBean("prototypeEmailService", EmailService.class);
        System.out.println(emailService3==emailService4);

        ApplicationContext context2 = new AnnotationConfigApplicationContext(AppConfig.class);
        EmailService emailService5 = context2.getBean("prototypeEmailService", EmailService.class);
        EmailService emailService6 = context2.getBean("prototypeEmailService", EmailService.class);
        System.out.println(emailService5==emailService6);

    }
}
