package com.bankdone.simple_bank_springboot.business.impl;

import com.bankdone.simple_bank_springboot.business.AccountService;
import com.bankdone.simple_bank_springboot.data_access.AccountRepository;
import com.bankdone.simple_bank_springboot.entity.Account;
import com.bankdone.simple_bank_springboot.entity.Agreement;
import com.bankdone.simple_bank_springboot.util.CreatorFakeEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for AccountServiceImplTest")
@Slf4j
class AccountServiceImplTest {
    @Mock
    AccountRepository accountRepository;
    Account accountTemplate;
    AccountService accountService ;

    List<Account> accountTemplateList;

    @BeforeEach
    public void setUp(){
        accountTemplate = CreatorFakeEntity.getFakeAccount(5L);
        log.info("AccountServiceImplTest - setUp - accountTemplate : {} ", accountTemplate );
        accountService = new AccountServiceImpl(accountRepository);
        accountTemplateList = new ArrayList<>(List.of(accountTemplate));

    }

    @Test
    void create() {
        when(accountRepository.save(any())).thenReturn(accountTemplate);
        Account result = accountService.create(accountTemplate);
        verify(accountRepository).save(any());
        assertEquals(accountTemplate, result);
    }

    @Test
    void edit() {
        when(accountRepository.save(any())).thenReturn(accountTemplate);
        Account result = accountService.edit(accountTemplate);
        verify(accountRepository).save(any());
        assertEquals(accountTemplate, result);
    }

    @Test
    void delite() {
        doNothing().when(accountRepository).deleteById(anyLong());
        accountService.delite(6L);
        verify(accountRepository).deleteById(anyLong());
    }

    @Test
    void getById() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(accountTemplate));
        Account result = accountService.getById(6L);
        log.info("AccountServiceImplTest - getById - result : {} ", result );
        verify(accountRepository).findById(anyLong());
        assertEquals(accountTemplate, result);


    }

    @Test
    void getAll() {
        when(accountRepository.findAll()).thenReturn(accountTemplateList);
        List<Account> resultList = accountService.getAll();
        verify(accountRepository).findAll();
        assertEquals(accountTemplateList, resultList);
    }
}