package fraud_detection.repository;

import fraud_detection.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );
}