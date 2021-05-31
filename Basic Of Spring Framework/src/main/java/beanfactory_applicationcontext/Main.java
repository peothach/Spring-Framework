package beanfactory_applicationcontext;

import beanfactory_applicationcontext.config.AppConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {

    public static void main(String[] args) {
        /**
         * The First Way (Use BeanFactory).
         */
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
        //BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring.xml");
        EmailService emailService1 = beanFactory.getBean("emailService", EmailService.class);
        emailService1.sendEmail("test1@gmail.com", "Hello from Miss Xing");

        /**
         * The Second Way (Use ApplicationContext).
         * With XML Application config and Class config.
         */
        ApplicationContext context1 = new ClassPathXmlApplicationContext("spring.xml");
        EmailService emailService2 = context1.getBean("emailService", EmailService.class);
        emailService2.sendEmail("test2@gmail.com", "Hello");

        ApplicationContext context2 = new AnnotationConfigApplicationContext(AppConfig.class);
        EmailService emailService3 = context2.getBean("emailService", EmailService.class);
        emailService3.sendEmail("test3@gmail.com", "Hi");

    }
}
