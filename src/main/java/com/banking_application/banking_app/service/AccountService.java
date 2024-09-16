package com.banking_application.banking_app.service;

import com.banking_application.banking_app.dto.AccountDto;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id) throws AccountNotFoundException;

    AccountDto deposit(Long id, Long amount) throws AccountNotFoundException;

    AccountDto withdraw(Long id, Long amount) throws AccountNotFoundException;

    List<AccountDto> getAllAccounts();

    void deleteAccountById(Long id) throws AccountNotFoundException;
}
