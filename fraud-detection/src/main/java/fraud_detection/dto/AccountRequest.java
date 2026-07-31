package fraud_detection.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

    private Long customerId;

    private String accountNumber;

    private Double balance;

    private String status;
}