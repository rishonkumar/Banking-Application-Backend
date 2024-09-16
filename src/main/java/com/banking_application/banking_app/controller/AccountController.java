package com.banking_application.banking_app.controller;

import com.banking_application.banking_app.dto.AccountDto;
import com.banking_application.banking_app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/account/create")
    public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) throws AccountNotFoundException {
        AccountDto accountDto = accountService.getAccountById(id);
        if(accountDto == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit (@PathVariable Long id,@RequestBody Map<String,Long> request) throws AccountNotFoundException {
        Long amount = request.get("amount");
        AccountDto accountDto =  accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto>withdrawal(@PathVariable Long id,@RequestBody Map<String,Long> request) throws AccountNotFoundException {
        Long amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>>getAllAcounts() {
        List<AccountDto>accountDtos = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) throws AccountNotFoundException {
        accountService.deleteAccountById(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
