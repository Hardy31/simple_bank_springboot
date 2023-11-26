package com.bankdone.simple_bank_springboot.presentation;

import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("account/{id}")
    public Account getById(@PathVariable Long id) {
        return accountService.getBiID(id);
    }

    @GetMapping("accounts/all")
    public List<Account> getAll(){
        return accountService.getAll();
    }

    @PostMapping("creatAccounts")
    public Account cteate(@RequestBody Account account) {
        return accountService.create(account);
    }


    @PutMapping("editAccount")
    public Account editAccount(@RequestBody Account account) {
        return accountService.edit(account);
    }

    @DeleteMapping("deleteAccount/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delite(id);
    }
}
