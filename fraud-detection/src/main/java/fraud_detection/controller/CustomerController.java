package fraud_detection.controller;

import fraud_detection.entity.Customer;
import fraud_detection.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id,
                                   @Valid @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    // SEARCH API
    @GetMapping("/search")
    public List<Customer> searchCustomers(@RequestParam String keyword) {

        return customerService.searchCustomers(keyword);

    }

}