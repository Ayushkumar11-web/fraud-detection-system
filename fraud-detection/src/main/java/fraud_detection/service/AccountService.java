package fraud_detection.service;

import fraud_detection.entity.Account;
import fraud_detection.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fraud_detection.dto.AccountRequest;
import fraud_detection.entity.Customer;
import fraud_detection.repository.CustomerRepository;

import java.util.List;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Save Account

    public Account saveAccount(AccountRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        Account account = new Account();

        account.setCustomer(customer);
        account.setAccountNumber(request.getAccountNumber());
        account.setBalance(request.getBalance());
        account.setStatus(request.getStatus());

        return accountRepository.save(account);
    }

    // Get All Accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Get Account By ID
    public Account getAccountById(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));
    }

    // Update Account
    public Account updateAccount(Long id, Account updatedAccount) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        account.setCustomer(updatedAccount.getCustomer());
        account.setAccountNumber(updatedAccount.getAccountNumber());
        account.setBalance(updatedAccount.getBalance());
        account.setStatus(updatedAccount.getStatus());

        return accountRepository.save(account);
    }

    // Delete Account
    public void deleteAccount(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        accountRepository.delete(account);
    }
}