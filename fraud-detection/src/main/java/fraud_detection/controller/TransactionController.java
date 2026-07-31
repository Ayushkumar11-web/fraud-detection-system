package fraud_detection.controller;

import fraud_detection.dto.TransactionDTO;
import fraud_detection.entity.Transaction;
import fraud_detection.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // ============================
    // Create Transaction
    // ============================
    @PostMapping
    public Transaction saveTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        return transactionService.saveTransaction(transactionDTO);
    }

    // ============================
    // Get All Transactions
    // ============================
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // ============================
    // Get Transaction By ID
    // ============================
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    // ============================
    // Get Transactions By Status
    // ============================
    @GetMapping("/status/{status}")
    public List<Transaction> getTransactionsByStatus(@PathVariable String status) {
        return transactionService.getTransactionsByStatus(status);
    }

    // ============================
    // Pagination + Sorting
    // ============================
    @GetMapping("/page")
    public Page<Transaction> getTransactionsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "timestamp") String sortBy) {

        return transactionService.getTransactionsPage(page, size, sortBy);
    }

    // ============================
    // Search Transactions
    // ============================
    @GetMapping("/search")
    public List<Transaction> searchTransactions(
            @RequestParam String keyword) {

        return transactionService.searchTransactions(keyword);
    }

    // ============================
    // Delete Transaction
    // ============================
    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "Transaction deleted successfully";
    }
}