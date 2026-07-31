package fraud_detection.controller;

import fraud_detection.entity.Account;
import fraud_detection.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import fraud_detection.dto.AccountRequest;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Add Account
    @PostMapping
    public Account saveAccount(@RequestBody AccountRequest request) {
        return accountService.saveAccount(request);
    }

    // Get All Accounts
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // Get Account By ID
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    // Update Account
    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id,
                                 @RequestBody Account account) {

        return accountService.updateAccount(id, account);
    }

    // Delete Account
    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);

        return "Account deleted successfully";
    }
}