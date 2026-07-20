package fraud_detection.service;

import fraud_detection.dto.TransactionDTO;
import fraud_detection.entity.Account;
import fraud_detection.entity.FraudAlert;
import fraud_detection.entity.Transaction;
import fraud_detection.repository.AccountRepository;
import fraud_detection.repository.FraudAlertRepository;
import fraud_detection.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FraudAlertRepository fraudAlertRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Save Transaction
    public Transaction saveTransaction(TransactionDTO dto) {

        if (dto.getAccountId() == null) {
            throw new RuntimeException("Account ID is required");
        }

        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(dto.getAmount());
        transaction.setTransactionType(dto.getTransactionType());
        transaction.setLocation(dto.getLocation());
        transaction.setTimestamp(LocalDateTime.now());

        if (dto.getAmount() > 50000) {

            transaction.setStatus("SUSPICIOUS");

            Transaction savedTransaction = transactionRepository.save(transaction);

            FraudAlert alert = new FraudAlert();
            alert.setTransaction(savedTransaction);
            alert.setReason("High Value Transaction");
            alert.setSeverity("HIGH");
            alert.setCreatedAt(LocalDateTime.now());

            fraudAlertRepository.save(alert);

            return savedTransaction;
        }

        transaction.setStatus("SUCCESS");
        return transactionRepository.save(transaction);
    }

    // Get All Transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Get Transaction By ID
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    // Delete Transaction
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    // Get Transactions By Status
    public List<Transaction> getTransactionsByStatus(String status) {
        return transactionRepository.findByStatus(status);
    }
}