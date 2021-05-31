package lifecycle.destruction;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Scope("singleton")
public class Customer implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean...");
    }

    @PreDestroy
    public void customDestroy(){
        System.out.println("CustomDestroy***");
    }
}
