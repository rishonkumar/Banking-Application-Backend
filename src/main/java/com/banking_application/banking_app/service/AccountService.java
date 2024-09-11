package com.banking_application.banking_app.service;

import com.banking_application.banking_app.dto.AccountDto;
import com.banking_application.banking_app.entity.Account;

import javax.security.auth.login.AccountNotFoundException;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id) throws AccountNotFoundException;
}
