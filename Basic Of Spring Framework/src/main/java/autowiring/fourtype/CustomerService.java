package autowiring.fourtype;

public class CustomerService {

    private  CustomerDAO customerDAO;

    public CustomerService() {
        System.out.println("Default constructor...");
    }

    public CustomerService(CustomerDAO customerDAO) {
        System.out.println("Customized constructor...");
        this.customerDAO = customerDAO;
    }

    public CustomerDAO getCustomerDAO(){
        return customerDAO;
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        System.out.println("Setter...");
        this.customerDAO = customerDAO;
    }
}
