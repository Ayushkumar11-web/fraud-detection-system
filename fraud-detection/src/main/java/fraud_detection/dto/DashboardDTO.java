package fraud_detection.dto;

public class DashboardDTO {

    private long totalCustomers;
    private long totalAccounts;
    private long totalTransactions;
    private long successfulTransactions;
    private long failedTransactions;
    private long suspiciousTransactions;
    private long totalFraudAlerts;

    public DashboardDTO() {
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public long getTotalAccounts() {
        return totalAccounts;
    }

    public void setTotalAccounts(long totalAccounts) {
        this.totalAccounts = totalAccounts;
    }

    public long getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(long totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public long getSuccessfulTransactions() {
        return successfulTransactions;
    }

    public void setSuccessfulTransactions(long successfulTransactions) {
        this.successfulTransactions = successfulTransactions;
    }

    public long getFailedTransactions() {
        return failedTransactions;
    }

    public void setFailedTransactions(long failedTransactions) {
        this.failedTransactions = failedTransactions;
    }

    public long getSuspiciousTransactions() {
        return suspiciousTransactions;
    }

    public void setSuspiciousTransactions(long suspiciousTransactions) {
        this.suspiciousTransactions = suspiciousTransactions;
    }

    public long getTotalFraudAlerts() {
        return totalFraudAlerts;
    }

    public void setTotalFraudAlerts(long totalFraudAlerts) {
        this.totalFraudAlerts = totalFraudAlerts;
    }
}