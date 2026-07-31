package fraud_detection.service;

import fraud_detection.dto.TransactionDTO;
import fraud_detection.entity.Account;
import fraud_detection.entity.FraudAlert;
import fraud_detection.entity.Transaction;
import fraud_detection.repository.AccountRepository;
import fraud_detection.repository.FraudAlertRepository;
import fraud_detection.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import fraud_detection.entity.Rule;
import fraud_detection.repository.RuleRepository;

import java.time.Duration;
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

    @Autowired
    private RuleRepository ruleRepository;

    // ============================
    // Create Transaction
    // ============================
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

        // Deposit / Withdraw
        if (dto.getTransactionType().equalsIgnoreCase("DEPOSIT")) {

            account.setBalance(account.getBalance() + dto.getAmount());
            accountRepository.save(account);

            transaction.setStatus("SUCCESS");

        } else if (dto.getTransactionType().equalsIgnoreCase("WITHDRAW")) {

            if (account.getBalance() < dto.getAmount()) {

                transaction.setStatus("FAILED");
                return transactionRepository.save(transaction);
            }

            account.setBalance(account.getBalance() - dto.getAmount());
            accountRepository.save(account);

            transaction.setStatus("SUCCESS");

        } else {
            throw new RuntimeException("Invalid Transaction Type");
        }

        // ============================
        // Fraud Detection Rules
        // ============================

        String reason = null;
        String severity = null;

        // Rule 1 : High Value
        // ============================
// Rule 1 : Dynamic Amount Rule
// ============================

        List<Rule> activeRules = ruleRepository.findByActiveTrue();

        for (Rule rule : activeRules) {

            if (dto.getAmount() > rule.getThresholdAmount()) {

                transaction.setStatus("SUSPICIOUS");

                reason = rule.getRuleName();

                severity = rule.getSeverity();

                break;

            }

        }

        // Rule 2 : Multiple Transactions within 5 minutes
        long recentCount =
                transactionRepository.countByAccountAndTimestampAfter(
                        account,
                        LocalDateTime.now().minusMinutes(5)
                );

        if (recentCount >= 3) {
            transaction.setStatus("SUSPICIOUS");
            reason = "Multiple Transactions within 5 Minutes";
            severity = "MEDIUM";
        }

        // Rule 3 : Different Location
        Transaction lastTransaction =
                transactionRepository.findTopByAccountOrderByTimestampDesc(account);

        if (lastTransaction != null) {

            long minutes = Duration.between(
                    lastTransaction.getTimestamp(),
                    LocalDateTime.now()
            ).toMinutes();

            if (minutes <= 10 &&
                    !lastTransaction.getLocation()
                            .equalsIgnoreCase(dto.getLocation())) {

                transaction.setStatus("SUSPICIOUS");
                reason = "Different Location Detected";
                severity = "HIGH";
            }
        }

        Transaction savedTransaction =
                transactionRepository.save(transaction);

        if ("SUSPICIOUS".equals(savedTransaction.getStatus())) {

            FraudAlert alert = new FraudAlert();

            alert.setTransaction(savedTransaction);
            alert.setReason(reason);
            alert.setSeverity(severity);
            alert.setCreatedAt(LocalDateTime.now());

            fraudAlertRepository.save(alert);
        }

        return savedTransaction;
    }

    // ============================
    // Get All Transactions
    // ============================
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // ============================
    // Get Transaction By Id
    // ============================
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    // ============================
    // Delete Transaction
    // ============================
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    // ============================
    // Get Transactions By Status
    // ============================
    public List<Transaction> getTransactionsByStatus(String status) {
        return transactionRepository.findByStatus(status);
    }

    // ============================
    // Pagination + Sorting
    // ============================
    public Page<Transaction> getTransactionsPage(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).descending()
        );

        return transactionRepository.findAll(pageable);
    }

    // ============================
    // Search Transactions
    // ============================
    public List<Transaction> searchTransactions(String keyword) {
        return transactionRepository.searchTransactions(keyword);
    }

}