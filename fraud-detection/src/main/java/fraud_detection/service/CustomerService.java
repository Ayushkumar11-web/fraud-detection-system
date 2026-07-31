package fraud_detection.service;

import fraud_detection.entity.Customer;
import fraud_detection.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer updateCustomer(Long id, Customer customer) {

        Customer existing = getCustomerById(id);

        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());

        return customerRepository.save(existing);
    }

    public String deleteCustomer(Long id) {

        customerRepository.deleteById(id);

        return "Customer deleted successfully";
    }

    // SEARCH
    public List<Customer> searchCustomers(String keyword) {

        return customerRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }

}