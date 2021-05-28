package lifecycle.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"lifecycle.javaconfig"})
public class AppConfig {

    @Bean(name = "custom", initMethod = "customInit", destroyMethod = "customDestroy")
    public Customer customer(){
        return new Customer();
    }
}
