package com.banking_application.banking_app.service.impl;

import com.banking_application.banking_app.dto.AccountDto;
import com.banking_application.banking_app.entity.Account;
import com.banking_application.banking_app.mapper.AccountMapper;
import com.banking_application.banking_app.repository.AccountRepository;
import com.banking_application.banking_app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
