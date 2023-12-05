package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.entity.Account;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface AccountService {

    List<Account> getAll() ;
    Account getById(Long id) ;
     Account create(Account account) ;
    Account edit(Account account) ;
    void delite(Long id) ;
   }
