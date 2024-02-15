package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.dto.AccountCreateDTO;
import com.bankdone.simple_bank_springboot.dto.AccountDTO;
import com.bankdone.simple_bank_springboot.dto.AccountListDTO;
import com.bankdone.simple_bank_springboot.entity.Account;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface AccountService {

    AccountListDTO getAll() ;
    AccountDTO getById(Long id) ;
    AccountDTO create(AccountCreateDTO accountCreateDTO) ;
    AccountDTO edit(AccountDTO accountDTO) ;
    void delite(Long id) ;
   }
