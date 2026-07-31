package fraud_detection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {

    private long totalCustomers;

    private long totalAccounts;

    private long totalTransactions;

    private long totalFraudAlerts;

    private long successfulTransactions;

    private long failedTransactions;

    private long suspiciousTransactions;

}