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

    public DashboardDTO getDashboardData() {

        long totalCustomers = customerRepository.count();
        long totalAccounts = accountRepository.count();
        long totalTransactions = transactionRepository.count();
        long totalFraudAlerts = fraudAlertRepository.count();

        DashboardDTO dashboardDTO = new DashboardDTO();

        dashboardDTO.setTotalCustomers(totalCustomers);
        dashboardDTO.setTotalAccounts(totalAccounts);
        dashboardDTO.setTotalTransactions(totalTransactions);
        dashboardDTO.setTotalFraudAlerts(totalFraudAlerts);

        return dashboardDTO;
    }
}