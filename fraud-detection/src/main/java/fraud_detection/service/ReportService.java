package fraud_detection.service;

import fraud_detection.dto.ReportDTO;
import fraud_detection.repository.AccountRepository;
import fraud_detection.repository.CustomerRepository;
import fraud_detection.repository.FraudAlertRepository;
import fraud_detection.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FraudAlertRepository fraudAlertRepository;

    public ReportDTO getReport() {

        ReportDTO report = new ReportDTO();

        report.setTotalCustomers(
                customerRepository.count()
        );

        report.setTotalAccounts(
                accountRepository.count()
        );

        report.setTotalTransactions(
                transactionRepository.count()
        );

        report.setTotalFraudAlerts(
                fraudAlertRepository.count()
        );

        report.setSuccessfulTransactions(
                transactionRepository.countByStatus("SUCCESS")
        );

        report.setFailedTransactions(
                transactionRepository.countByStatus("FAILED")
        );

        report.setSuspiciousTransactions(
                transactionRepository.countByStatus("SUSPICIOUS")
        );

        return report;

    }

}