package lifecycle.javaconfig;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Customer{

    public Customer(){
        System.out.println("Default constructor ...");
    }

    @PostConstruct
    public void customInit(){
        System.out.println("Customer init...");
    }

    @PreDestroy
    public void  customDestroy(){
        System.out.println("Custom destroy...");
    }
}
