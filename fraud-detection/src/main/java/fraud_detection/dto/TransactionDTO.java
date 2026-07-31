package fraud_detection.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransactionDTO {

    @NotNull(message = "Account ID is required")
    private Long accountId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private Double amount;

    @NotBlank(message = "Transaction Type is required")
    private String transactionType;

    @NotBlank(message = "Location is required")
    private String location;

    public TransactionDTO() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}