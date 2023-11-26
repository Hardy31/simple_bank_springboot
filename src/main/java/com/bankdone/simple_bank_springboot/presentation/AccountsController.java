package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class AccountsController {

    @Autowired
    AccountService accountService;

    @GetMapping("accounts")
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @GetMapping("accounts/{id}")
    public Account getById(@PathVariable Long id) {
        return accountService.getBiId(id);
    }

    @PostMapping("accounts")
    public Account cteate(@RequestBody Account account) {
        return accountService.create(account);
    }

    @PutMapping("accounts")
    public Account editAccount(@RequestBody Account account) {
        return accountService.edit(account);
    }

    @DeleteMapping("accounts/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delite(id);
    }

}
