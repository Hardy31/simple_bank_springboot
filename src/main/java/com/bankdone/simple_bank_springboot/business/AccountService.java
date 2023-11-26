package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    public List<Account> getAll(){
        return (List<Account>) accountRepository.findAll();
    }

    public Account getBiId(Long id){
        return accountRepository.findById(id).get();
    }
    public Account getBiID(Long id){
        return accountRepository.findByID(id).get();
    }

    public Account create(Account account){
        System.out.println(account);
        return  accountRepository.save(account);
    }

    public Account edit(Account account){
        return accountRepository.save(account);
    }

    public void delite(Long id){
        accountRepository.deleteById(id);
    }

}
