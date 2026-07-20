package fraud_detection.dto;

public class DashboardDTO {

    private long totalCustomers;
    private long totalAccounts;
    private long totalTransactions;
    private long totalFraudAlerts;

    public DashboardDTO() {
    }

    public DashboardDTO(long totalCustomers,
                        long totalAccounts,
                        long totalTransactions,
                        long totalFraudAlerts) {
        this.totalCustomers = totalCustomers;
        this.totalAccounts = totalAccounts;
        this.totalTransactions = totalTransactions;
        this.totalFraudAlerts = totalFraudAlerts;
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

    public long getTotalFraudAlerts() {
        return totalFraudAlerts;
    }

    public void setTotalFraudAlerts(long totalFraudAlerts) {
        this.totalFraudAlerts = totalFraudAlerts;
    }
}