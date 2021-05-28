package lifecycle.creation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("customer")
public class Customer implements BeanNameAware ,BeanFactoryAware, ApplicationContextAware, InitializingBean {

    private String firstName;

    public Customer(){
        System.out.println("Customer non-arg constructor...");
    }

    public Customer(String firstName){
        System.out.println("Customer First Name constructor...");
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        System.out.println("SetFirstName..." + firstName);
        this.firstName = firstName;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("SetBeanName..." + s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("SetBeanFactory..." + beanFactory.isSingleton("customer"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("SetApplicationContext..." + applicationContext.isSingleton("customer"));
        this.firstName = "Xing in Application Context";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet....");
        this.firstName = "Xing in afterPropertiesSet";
    }

    @PostConstruct
    public void customInit(){
        System.out.println("customInit....");
        this.firstName = "Xing in customInit";
    }

}
