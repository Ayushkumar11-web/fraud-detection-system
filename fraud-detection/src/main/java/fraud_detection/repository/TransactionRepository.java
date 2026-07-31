package fraud_detection.repository;

import fraud_detection.entity.Account;
import fraud_detection.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Dashboard
    long countByStatus(String status);

    // Fraud Rule
    long countByAccountAndTimestampAfter(Account account, LocalDateTime time);

    Transaction findTopByAccountOrderByTimestampDesc(Account account);

    // Filter
    List<Transaction> findByStatus(String status);

    // Search
    @Query("""
        SELECT t FROM Transaction t
        WHERE LOWER(t.account.accountNumber) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(t.status) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(t.location) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Transaction> searchTransactions(@Param("keyword") String keyword);
}