package com.banking_application.banking_app.service.impl;

import com.banking_application.banking_app.controller.AccountController;
import com.banking_application.banking_app.dto.AccountDto;
import com.banking_application.banking_app.entity.Account;
import com.banking_application.banking_app.mapper.AccountMapper;
import com.banking_application.banking_app.repository.AccountRepository;
import com.banking_application.banking_app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    // account dto -> account JPA entity and then we will save the account
    //Jpa entity into a database so this is the conversion logic right lets create
    // a mapper class and right a common logic

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        //return type is dto so map it to dto and return
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) throws AccountNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account doesnt not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Long amount) throws AccountNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account doesnt exists"));
        if(account != null) {
            Long currentBalance = account.getBalance();
            Long updatedBalance = currentBalance + amount;
            account.setBalance(updatedBalance);
            Account savedAccount =  accountRepository.save(account);
            return AccountMapper.mapToAccountDto(savedAccount);
        }
        throw new AccountNotFoundException("Account doesnt exists");
    }

    @Override
    public AccountDto withdraw(Long id, Long amount) throws AccountNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account doesnt exists"));

        if(account != null) {
            Long currentBalance = account.getBalance();
            if(amount > currentBalance) {
                throw new RuntimeException("Withdraw amount exceeds current balance");
            } else {
                account.setBalance(currentBalance - amount);
                Account savedAccount = accountRepository.save(account);
                return AccountMapper.mapToAccountDto(savedAccount);
            }
        }
        throw new AccountNotFoundException("Account doesnt exists");
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account>accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccountById(Long id) throws AccountNotFoundException {
        if(accountRepository.findById(id).isPresent()) {
            accountRepository.deleteById(id);
        }
    }
}
