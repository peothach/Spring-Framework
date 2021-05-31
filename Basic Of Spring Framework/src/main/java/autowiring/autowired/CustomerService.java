package autowiring.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    @Qualifier("customerDao")
    private ICustomerDao customerDAO;

    public CustomerService() {
        System.out.println("Constructor no-args");
    }

    //@Autowired
    public CustomerService(ICustomerDao customerDAO) {
        System.out.println("Customized constructor");
        this.customerDAO = customerDAO;
    }

    //@Autowired
    public void setCustomerDAO(CustomerDaoImpl1 customerDAO) {
        System.out.println("Setter...");
        this.customerDAO = customerDAO;
    }
}
