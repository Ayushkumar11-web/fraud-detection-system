package fraud_detection.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @NotBlank(message = "First Name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Phone Number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone Number must be 10 digits"
    )
    @Column(nullable = false)
    private String phone;
}