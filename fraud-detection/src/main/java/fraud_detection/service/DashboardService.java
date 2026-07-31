package fraud_detection.service;

import fraud_detection.dto.DashboardDTO;
import fraud_detection.repository.AccountRepository;
import fraud_detection.repository.CustomerRepository;
import fraud_detection.repository.FraudAlertRepository;
import fraud_detection.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FraudAlertRepository fraudAlertRepository;

    public DashboardDTO getDashboard() {

        DashboardDTO dashboard = new DashboardDTO();

        dashboard.setTotalCustomers(customerRepository.count());

        dashboard.setTotalAccounts(accountRepository.count());

        dashboard.setTotalTransactions(transactionRepository.count());

        dashboard.setSuccessfulTransactions(
                transactionRepository.countByStatus("SUCCESS")
        );

        dashboard.setFailedTransactions(
                transactionRepository.countByStatus("FAILED")
        );

        dashboard.setSuspiciousTransactions(
                transactionRepository.countByStatus("SUSPICIOUS")
        );

        dashboard.setTotalFraudAlerts(
                fraudAlertRepository.count()
        );

        return dashboard;
    }
}