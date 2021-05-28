## How does Spring know to search Compoment or Bean ?
> Có 2 cách:
> - @ComponentScan
> - XML application context.
> - Java Class Config.

## @ComponentScan
```java
@ComponentScan({"me.loda.spring.componentscan.others2","me.loda.spring.componentscan.others"})
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        try {
            Girl girl = context.getBean(Girl.class);
            System.out.println("Bean: " + girl.toString());
        } catch (Exception e) {
            System.out.println("Bean Girl không tồn tại");
        }

        try {
            OtherGirl otherGirl = context.getBean(OtherGirl.class);
            if (otherGirl != null) {
                System.out.println("Bean: " + otherGirl.toString());
            }
        } catch (Exception e) {
            System.out.println("Bean Girl không tồn tại");
        }
    }
}
}
```

## Java Class Config
```java
@Configuration
public class AppConfig {

    @Bean
    public EmailService emailService(){
        return new EmailService();
    }

    @Bean
    @Scope("prototype")
    public EmailService prototypeEmailService(){
        return new EmailService();
    }
}
```

## XML application context
```java
<context:component-scan base-package="com.in28minutes.package1, com.in28minutes.package2" />
```