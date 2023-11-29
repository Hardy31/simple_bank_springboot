package com.bankdone.simple_bank_springboot.business;

import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Cacheable("Accounts")
    public List<Account> getAll() {
        return (List<Account>) accountRepository.findAll();
    }

    public Account getBiId(Long id) {
        return accountRepository.findById(id).get();
    }

    public Account getBiID(Long id) {
        return accountRepository.findByID(id).get();
    }

    @CacheEvict("Accounts")
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @CacheEvict("Accounts")
    public Account edit(Account account) {
        return accountRepository.save(account);
    }

    @CacheEvict("Accounts")
    public void delite(Long id) {
        accountRepository.deleteById(id);
    }

}
