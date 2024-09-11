package com.banking_application.banking_app.repository;

import com.banking_application.banking_app.dto.AccountDto;
import com.banking_application.banking_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {


}
